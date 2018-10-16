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

import com.github.loki.handler.RequestMockHandler;
import com.github.loki.response.GetResponseTemplate;
import com.github.loki.response.ResponseTemplateRepository;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class responsible for a start server
 *
 * @author Flavio Andrade
 */
@Slf4j
@Component
public class StartMockServer {

    @Autowired
    private GetResponseTemplate getResponseTemplate;
    
    @Autowired
    private ResponseTemplateRepository repository;

    @Autowired
    @Qualifier("poolServer")
    private Map<Integer, Server> poolServer;

    /**
     * Starts all servers configured
     */
    public void all() {

        Set<Integer> errorsStartServer = new HashSet();
        Set<Integer> ports = repository.listAllPorts();

        for (Integer port : ports) {

            try {

                start(port);

            } catch (MockServerException mse) {

                errorsStartServer.add(port);
            }
        }

        if (!errorsStartServer.isEmpty()) {

            throw new MockServerException("An error occurred while starting servers " + errorsStartServer);
        }
    }

    /**
     * Start a specifc configured server
     *
     * @param port of server that must be started
     */
    public void start(Integer port) {

        if (poolServer.containsKey(port)) {

            log.warn("this server already exists");
            throw new MockServerException("there is already an instantiated server on this port");
        }

        Server server = new Server(port);
        server.setHandler(new RequestMockHandler(port, getResponseTemplate));
        
        try {
            
            log.info("starting server {}", port);
            server.start();
            
        } catch (Exception ex) {
            
            log.error("error starting server", ex);
            throw new MockServerException(ex.getMessage());
        }
        
        poolServer.put(port, server);
        log.info("new server started on port {}", port);
    }

}
