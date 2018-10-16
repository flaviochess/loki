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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class responsible to stop servers
 *
 * @author Flavio Andrade
 */
@Slf4j
@Component
public class StopMockServer {

    @Autowired
    @Qualifier("poolServer")
    private Map<Integer, Server> poolServer;

    /**
     * Stop a specifc server by port
     *
     * @param port of server that must be stoped
     */
    public void fromPort(Integer port) {

        Server server = poolServer.get(port);

        if (server == null) {

            log.warn("this server does not exists");
            throw new MockServerException("there is does not an instantiated server on this port");
        }

        log.info("stopping server {}", port);
        stopServer(server);
        
        poolServer.remove(port);

    }

    /**
     * Stop all servers that are running
     */
    public void all() {
        
        Set<Integer> stoppedServers = new HashSet();
        Set<Integer> errorsStopServer = new HashSet();
        
        for (Map.Entry<Integer, Server> entryServer : poolServer.entrySet()) {
            
            Integer port = entryServer.getKey();
            Server server = entryServer.getValue();
            
            try {
                
                log.info("stopping server {}", port);
                stopServer(server);
                stoppedServers.add(port);
                
            } catch (MockServerException mse) {
                
                log.warn("Server port {} did not stop", port);
                errorsStopServer.add(port);
            }
        }
        
        stoppedServers.stream().forEach(poolServer::remove);
        
        if (!errorsStopServer.isEmpty()) {
            
            throw new MockServerException("An error occurred while stopping servers " + errorsStopServer);
        }
    }
    
    private void stopServer(Server server) {
        
        try {
            
            server.stop();
        } catch (Exception ex) {

            log.error("error stoping server", ex);
            throw new MockServerException(ex.getMessage());
        }
        
    }
    
}
