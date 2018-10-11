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
 *
 * @author Flavio Andrade
 */
@Slf4j
@Component
public class StopMockServer {

    @Autowired
    @Qualifier("poolServer")
    private Map<Integer, Server> poolServer;

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
