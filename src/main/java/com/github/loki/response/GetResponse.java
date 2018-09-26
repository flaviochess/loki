package com.github.loki.response;

import org.springframework.stereotype.Component;

/**
 *
 * @author Flavio Andrade
 */
@Component //trocar para repositorio
public class GetResponse {
    
    public ResponseConfig byPortAndMethodAndUri(Integer port, String method, String uri) {
        
        if(uri.equalsIgnoreCase("/loki/test")) {
            
            return response200(port, method);
        }
        
        return response404(port, method);
    }
    
    private ResponseConfig response200(Integer port, String method) {
        
        ResponseConfig responseConfig = new ResponseConfig();
        
        responseConfig.setPort(port);
        responseConfig.setMethod(method);
        responseConfig.setStatusCode(200);
        responseConfig.setBody("[{\"account\":{\"id\":709918,\"description\":\"Brasil Account\",\"personId\":28333,\"name\":\"Flavio Andrade\",\"model\":\"XPTO\",\"country\":\"BR\",\"enabled\":true},\"profile\":{\"id\":\"ADMIN\",\"name\":\"ADMIN\",\"standard\":true,\"order\":1}}]");
        
        return responseConfig;
    }
    
    private ResponseConfig response404(Integer port, String method) {
        
        ResponseConfig responseConfig = new ResponseConfig();
        
        responseConfig.setPort(port);
        responseConfig.setMethod(method);
        responseConfig.setStatusCode(404);
        responseConfig.setBody("");
        
        return responseConfig;
    }
}
