package com.github.loki.response;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Flavio Andrade
 */
@Getter
@Setter
public class ResponseConfig {
    
    private Integer port;
    
    private String method;
    
    private String uri;
    
    private Integer statusCode;
    
    private String body;
    
}
