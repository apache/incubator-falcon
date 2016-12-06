/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.falcon.unit;

import org.apache.falcon.FalconException;
import org.apache.falcon.Pair;
import org.apache.falcon.entity.v0.feed.Feed;
import org.apache.falcon.entity.v0.process.Process;
import org.apache.falcon.resource.APIResult;
import org.apache.falcon.resource.extensions.ExtensionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * A proxy implementation of the extension operations in local mode.
 */
public class LocalExtensionManager extends ExtensionManager {
    public LocalExtensionManager() {}

    public APIResult submitExtensionJob(String extensionName, String jobName, InputStream config, List<Feed> feeds,
                                        List<Process> processes) throws FalconException, IOException {
        submitEntities(extensionName, null, jobName, feeds, processes, config);
        return new APIResult(APIResult.Status.SUCCEEDED, "Extension job submitted successfully" + jobName);
    }

    public APIResult submitAndSchedulableExtensionJob(String extensionName, String jobName, InputStream config,
        List<Feed> feeds, List<Process> processes) throws FalconException, IOException {
        submitEntities(extensionName, null, jobName, feeds, processes, config);
        scheduleEntities(new Pair(feeds, processes));
        return new APIResult(APIResult.Status.SUCCEEDED, "Extension job submitted successfully" + jobName);
    }


    public String registerExtensionMetadata(String extensionName, String packagePath , String description) {
        return super.registerExtensionMetadata(extensionName, packagePath, description);
    }

    public String unRegisterExtension(String extensionName) {
        return super.deleteExtensionMetadata(extensionName);
    }

}
