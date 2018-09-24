package com.github.loki.server;

import org.eclipse.jetty.server.Server;

/**
 *
 * @author Flavio Andrade
 */
public class CreateNewServer {

    public static void create(Integer port) throws Exception {

        Server server = new Server(port);
        server.start();
        server.dumpStdErr();
        server.join();
    }
}
