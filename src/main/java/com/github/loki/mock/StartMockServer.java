package com.github.loki.mock;

import com.github.loki.handler.RequestMockHandler;
import com.github.loki.response.GetResponseTemplate;
import java.util.Map;
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
public class StartMockServer {

    @Autowired
    private GetResponseTemplate getResponseTemplate;

    @Autowired
    @Qualifier("poolServer")
    private Map<Integer, Server> poolServer;

    public void start(Integer port) {

        if (poolServer.containsKey(port)) {

            log.warn("this server already exists");
            throw new MockServerException("there is already an instantiated server on this port");
        }

        Server server = new Server(port);
        server.setHandler(new RequestMockHandler(port, getResponseTemplate));
        
        try {
            
            server.start();
            
        } catch (Exception ex) {
            
            log.error("error starting server", ex);
            throw new MockServerException(ex.getMessage());
        }
        
        poolServer.put(port, server);
        log.info("new server started on port {}", port);
    }

}
