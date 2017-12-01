package org.rapidoid.benchmark.common;

/*
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

public class Helper {

	// most of this configuration was copied from the other projects
	public static final String MYSQL_CONFIG = "useSSL=false&jdbcCompliantTruncation=false" +
		"&elideSetAutoCommits=true&useLocalSessionState=true&cachePrepStmts=true&cacheCallableStmts=true" +
		"&alwaysSendSetIsolation=false&cacheServerConfiguration=true" +
		"&zeroDateTimeBehavior=convertToNull&traceProtocol=false&enableQueryTimeouts=false" +
		"&useUnbufferedIO=false&useReadAheadInput=false&maintainTimeStats=false" +
		"&cacheRSMetadata=true&useServerPrepStmts=true&serverTimezone=UTC";

	public static final String POSTGRES_CONFIG = "";

}
