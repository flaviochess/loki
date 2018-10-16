/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.loki.response;

import com.github.loki.files.GetMockFilesConfigurations;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Responsible to update response template configurations
 *
 * @author Flavio Andrade
 */
@Component
public class UpdateResponseTemplate {
    
    @Autowired
    private GetMockFilesConfigurations getMockFilesConfigurations;
    
    @Autowired
    private ResponseTemplateRepository repository;

    /**
     * Erase actual response templates and refresh with values from file
     * configurations only has settings inside.
     */
    public void fromFiles() {
        
        List<ResponseTemplate> responseTemplates = getMockFilesConfigurations.mapToResponseTemplate();
        
        if(responseTemplates.isEmpty()) {
            return;
        }
        
        repository.deleteAll();
        
        responseTemplates.forEach(repository::save);
    }
}
