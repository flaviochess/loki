package com.github.loki.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Flavio Andrade
 */
@RestController
@RequestMapping("responseTemplate")
public class ResponseTemplateController {
    
    @Autowired
    private UpdateResponseTemplate updateResponseTemplate;
    
    @PostMapping("refresh")
    public void refresh() {
        
        updateResponseTemplate.fromFiles();
    }
}
