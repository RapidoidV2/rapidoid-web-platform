/*-
 * #%L
 * rapidoid-benchmark
 * %%
 * Copyright (C) 2014 - 2017 Nikolche Mihajlovski
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

package org.rapidoid.benchmark.highlevel;


import org.rapidoid.benchmark.common.Fortune;
import org.rapidoid.http.Req;
import org.rapidoid.http.ReqRespHandler;
import org.rapidoid.http.Resp;
import org.rapidoid.jdbc.JdbcClient;
import org.rapidoid.lambda.Mapper;
import org.rapidoid.render.Template;
import org.rapidoid.render.Templates;
import org.rapidoid.u.U;
import org.rapidoid.util.Wait;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FortunesMultiHandler implements ReqRespHandler {

	private static final String SQL = "SELECT id, message FROM fortune";

	private static final Mapper<ResultSet, Fortune> resultMapper = rs -> new Fortune(rs.getInt(1), rs.getString(2));

	private final Template template = Templates.load("fortunes.html");

	private final JdbcClient jdbc;

	public FortunesMultiHandler(JdbcClient jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Object execute(Req req, Resp resp) throws Exception {

		int count = U.num(req.param("n", "1000"));

		CountDownLatch latch = new CountDownLatch(count);

		for (int i = 0; i < count; i++) {
			jdbc.execute(resultMapper, (List<Fortune> fortunes, Throwable err) -> {

				if (err == null) {
					fortunes.add(new Fortune(0, "Additional fortune added at request time."));
					Collections.sort(fortunes);
					template.renderToBytes(fortunes);
				}

			}, SQL);
		}

		Wait.on(latch);

		return "OK".getBytes();
	}


}
