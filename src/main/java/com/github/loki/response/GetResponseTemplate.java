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

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

/**
 * Reponsible to get a response template that matches the request
 *
 * @author Flavio Andrade
 */
@Service
public class GetResponseTemplate {
    
    @Autowired
    private ResponseTemplateRepository repository;

    /**
     * Get response template by port, method and uri
     *
     * @param port to find the response template
     * @param method to find the response template
     * @param uri to find the response template
     * @return a configured response template or a 404 response template
     */
    public ResponseTemplate byPortAndMethodAndUri(Integer port, String method, String uri) {
        
        Optional<ResponseTemplate> responseConfig = repository.findByPortAndMethodAndUri(port, method, uri);
        
        if(responseConfig.isPresent()) {
           return responseConfig.get();
        }
        
        List<ResponseTemplate> responseTemplates = repository.findByPortAndMethodOrderByIdAsc(port, method);
        
        responseConfig = responseTemplates.stream()
                .filter(template -> new AntPathMatcher().match(template.getUri(), uri))
                .findFirst();
        
        return responseConfig.orElse(response404(port, method));
    }
    
    //TODO: analisar a possibilidade de usar um ErrorHandler para esses casos
    private ResponseTemplate response404(Integer port, String method) {
        
        ResponseTemplate responseConfig = new ResponseTemplate();
        
        responseConfig.setPort(port);
        responseConfig.setMethod(method);
        responseConfig.setStatusCode(404);
        responseConfig.setBody("{\"status\": 404, \"error\": \"Not Found\", \"message\": \"Not Found\"}");
        
        return responseConfig;
    }
}
