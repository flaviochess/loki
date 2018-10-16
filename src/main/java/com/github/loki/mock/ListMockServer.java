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
package com.github.loki.mock;

import com.github.loki.response.ResponseTemplateRepository;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;
import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Class responsible for list informations about intances of server
 *
 * @author Flavio Andrade
 */
@Component
public class ListMockServer {
   
    @Autowired
    private ResponseTemplateRepository repository;

    @Autowired
    @Qualifier("poolServer")
    private Map<Integer, Server> poolServer;

    /**
     * List ports and status of all server's intance running and all not
     * running.
     *
     * @return a list of ports and status (UP or DOWN) of each port
     */
    public Map<Integer, String> portStatus() {
    
        Set<Integer> ports = repository.listAllPorts();
        
        ports.addAll(poolServer.keySet());
        
        return ports
                .stream()
                .collect(toMap(
                        Function.identity(), 
                        x -> poolServer.containsKey(x)? "UP" : "DOWN"));
    }
}
