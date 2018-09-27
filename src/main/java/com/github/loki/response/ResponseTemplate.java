package com.github.loki.response;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Flavio Andrade
 */
@Getter
@Setter
@Entity
public class ResponseTemplate implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private Integer port;
    
    private String method;
    
    private String uri;
    
    private Integer statusCode;
    
    private String body;
    
}
