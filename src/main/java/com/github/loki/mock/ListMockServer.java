package com.github.loki.mock;

import com.github.loki.response.ResponseTemplateRepository;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
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
    
    public Map<Integer, String> portStatus() {
    
        Set<Integer> ports = repository.listAllPorts();
        
        return ports
                .stream()
                .collect(
                        Collectors.toMap(Function.identity(), x -> poolServer.containsKey(x)? "UP" : "DOWN"));
    }
}
