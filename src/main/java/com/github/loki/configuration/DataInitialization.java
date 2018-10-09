package com.github.loki.configuration;

import com.github.loki.files.MockFileException;
import com.github.loki.response.UpdateResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Flavio Andrade
 */
@Slf4j
@Component
public class DataInitialization {
    
    @Autowired
    private UpdateResponseTemplate updateResponseTemplate;
    
    @EventListener(ApplicationReadyEvent.class)
    public void readMockFiles() {

        try {
            
            updateResponseTemplate.fromFiles();
            
        } catch (MockFileException mfe) {
            
            log.error("Failed to initialize data", mfe);
        }
    }
}
