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
