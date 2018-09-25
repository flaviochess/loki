package com.github.loki.server;

import com.github.loki.handler.RequestMockHandler;
import org.eclipse.jetty.server.Server;

/**
 *
 * @author Flavio Andrade
 */
public class CreateNewServer {

    public static void create(Integer port) throws Exception {

        Server server = new Server(port);
        server.setHandler(new RequestMockHandler(port));

        server.start();
        server.join();
    }
}
