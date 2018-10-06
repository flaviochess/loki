package com.github.loki.response;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

/**
 *
 * @author Flavio Andrade
 */
@Service
public class GetResponseTemplate {
    
    @Autowired
    private ResponseTemplateRepository repository;
    
    public ResponseTemplate byPortAndMethodAndUri(Integer port, String method, String uri) {
        
        Optional<ResponseTemplate> responseConfig = repository.findByPortAndMethodAndUri(port, method, uri);
        
        if(responseConfig.isPresent()) {
           return responseConfig.get();
        }
        
        List<ResponseTemplate> responseTemplates = repository.findByPortAndMethodOrderByIdAsc(port, method);
        
        responseConfig = responseTemplates.stream()
                .filter(template -> new AntPathMatcher().match(template.getUri(), uri))
                .findFirst();
        
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
