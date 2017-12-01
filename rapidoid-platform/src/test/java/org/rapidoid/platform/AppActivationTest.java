package org.rapidoid.platform;

/*
 * #%L
 * rapidoid-platform
 * %%
 * Copyright (C) 2014 - 2017 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Test;
import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.env.Env;
import org.rapidoid.util.Msc;

import static org.rapidoid.test.TestCommons.createTempDir;

@Authors("Nikolche Mihajlovski")
@Since("5.4.7")
public class AppActivationTest extends PlatformTestCommons {

	@Test
	public void testAppDeployment() {
		String appsDir = createTempDir("app");
		PlatformOpts.singleAppPath(appsDir);

		isFalse(Msc.isPlatform());
		isTrue(PlatformOpts.isSingleApp());
		isFalse(Env.isInitialized());

		runMain("mode=production");

		isTrue(Env.isInitialized());
		isTrue(Msc.isPlatform());
		isTrue(PlatformOpts.isSingleApp());
		isTrue(Env.production());
	}

}
