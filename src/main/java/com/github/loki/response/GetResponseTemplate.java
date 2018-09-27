package com.github.loki.response;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Flavio Andrade
 */
@Repository
public class GetResponseTemplate {
    
    @Autowired
    private ResponseTemplateRepository repository;
    
    public ResponseTemplate byPortAndMethodAndUri(Integer port, String method, String uri) {
        
        Optional<ResponseTemplate> responseConfig = repository.findByPortAndMethodAndUri(port, method, uri);
        
        return responseConfig.orElse(response404(port, method));
    }
    
    //TODO: analisar a possibilidade de usar um ErrorHandler para esses casos
    private ResponseTemplate response404(Integer port, String method) {
        
        ResponseTemplate responseConfig = new ResponseTemplate();
        
        responseConfig.setPort(port);
        responseConfig.setMethod(method);
        responseConfig.setStatusCode(404);
        responseConfig.setBody("{\"status\": 404, \"error\": \"Not Found\", \"message\": \"Not Found\"}");
        
        return responseConfig;
    }
}
