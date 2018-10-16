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
package com.github.loki.configuration;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.jetty.server.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 *
 * @author Flavio Andrade
 */
@Configuration
public class DeclarationBean {
    
    @Bean(name = "poolServer")
    @ApplicationScope
    public Map<Integer, Server> poolServer() {
        
        Map<Integer, Server> poolServer = new HashMap();
        return poolServer;
    }
    
}
