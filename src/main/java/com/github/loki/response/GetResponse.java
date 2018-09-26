package com.github.loki.response;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Flavio Andrade
 */
@Repository
public class GetResponse {
    
    @Autowired
    private ResponseConfigRepository repository;
    
    public ResponseConfig byPortAndMethodAndUri(Integer port, String method, String uri) {
        
        Optional<ResponseConfig> responseConfig = repository.findByPortAndMethodAndUri(port, method, uri);
        
        return responseConfig.orElse(response404(port, method));
    }
    
    private ResponseConfig response404(Integer port, String method) {
        
        ResponseConfig responseConfig = new ResponseConfig();
        
        responseConfig.setPort(port);
        responseConfig.setMethod(method);
        responseConfig.setStatusCode(404);
        responseConfig.setBody("{\"status\": 404, \"error\": \"Not Found\", \"message\": \"Not Found\"}");
        
        return responseConfig;
    }
}
