package org.rapidoid.goodies;

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

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.deploy.handler.AppDeploymentHandler;
import org.rapidoid.deploy.handler.AppStagingHandler;
import org.rapidoid.deploy.handler.DeployHandler;
import org.rapidoid.setup.Setup;

@Authors("Nikolche Mihajlovski")
@Since("5.3.3")
public class PlatformGoodies extends Goodies {

	public static void processes(Setup setup) {
		setup.page(uri("processes")).zone(CENTER).mvc(new ProcessesHandler());
		setup.page(uri("processes/{id}")).zone(CENTER).mvc(new ProcessDetailsHandler());
	}

	public static void deployment(Setup setup) {
		setup.page(uri("deployment")).zone(Goodies.CENTER).mvc(new DeployHandler());
		setup.post(uri("stage")).zone(Goodies.CENTER).json(new AppStagingHandler());
		setup.post(uri("deploy")).zone(Goodies.CENTER).json(new AppDeploymentHandler());
	}

	public static void platformAdminCenter(Setup setup) {
		adminCenter(setup);
		deployment(setup);
		processes(setup);
	}

}
