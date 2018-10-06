package com.github.loki.response;

import com.github.loki.files.GetMockFilesConfigurations;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Flavio Andrade
 */
@Component
public class UpdateResponseTemplate {
    
    @Autowired
    private GetMockFilesConfigurations getMockFilesConfigurations;
    
    private ResponseTemplateRepository repository;
    
    public void fromFiles() {
        
        List<ResponseTemplate> responseTemplates = getMockFilesConfigurations.mapToResponseTemplate();
        
        //verifica se esta null
        
        //apaga todas as configurações atuais
        
        //insere as "novas"
    }
}
