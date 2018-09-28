package com.github.loki.mock;

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

        try {

            server.stop();
        } catch (Exception ex) {

            log.error("error stoping server", ex);
            throw new MockServerException(ex.getMessage());
        }

    }
}
