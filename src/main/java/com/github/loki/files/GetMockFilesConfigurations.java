package com.github.loki.files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.loki.response.ResponseTemplate;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Flavio Andrade
 */
@Slf4j
@Component
public class GetMockFilesConfigurations {
    
    private static final String SLASH = "/";
    
    @Value("${loki.path.configuration.files}")
    private String pathConfigurationFiles;
    
    public List<ResponseTemplate> mapToResponseTemplate() {
        
        Path folder = Paths.get(pathConfigurationFiles);
        
        if(!isValidDirectory(folder)) {
            
            log.warn("Invalid path configuration files: {}", pathConfigurationFiles);
            throw new MockFileException("Invalid path configuration files: " + pathConfigurationFiles);
        }
        
        ObjectMapper mapper = new ObjectMapper();
        List<ResponseTemplate> responseTemplates = new ArrayList();
        DirectoryStream<Path> configurationPaths;
        
        try {
            
            configurationPaths = Files.newDirectoryStream(folder, p -> p.getFileName().toString().endsWith(".json"));
            
        } catch (IOException iOException) {
            
            log.error("Fail to find files in the folder {}", pathConfigurationFiles, iOException);
            throw new MockFileException("Fail to find files in the folder " + pathConfigurationFiles);
        }

        for (Path configurationPath : configurationPaths) {
            
            try {
                
                log.info("Tries to read the file {}", configurationPath.getFileName());
                ConfigurationObject configurationObject = mapper.readValue(configurationPath.toFile(), ConfigurationObject.class);
                
                responseTemplates.addAll(convertTo(configurationObject));
                log.info("File {} OK", configurationPath.getFileName());
                
            } catch (IOException iOException) {
                
                log.error("Error to read file {}", configurationPath.getFileName(), iOException);
            }
        }
        
        log.info("{} response templates found", responseTemplates.size());
        return responseTemplates;
    }
    
    private boolean isValidDirectory(Path path) {
        
        return Files.exists(path) && Files.isDirectory(path);
    }
    
    //será que mantem a ordem do arquivo? É importante manter a ordem de configuração do arquivo
    private List<ResponseTemplate> convertTo(ConfigurationObject configurationObject) {
        
        Integer port = configurationObject.getPort();
        
        String context = configurationObject.getContext();
        
        return configurationObject.getEntries().stream()
                .map(entry -> convertTo(port, context, entry))
                .collect(Collectors.toList());
    }
    
    private ResponseTemplate convertTo(Integer port, String context, ConfigurationObject.Entry entry) {
        
        ResponseTemplate responseTemplate = new ResponseTemplate();
        
        responseTemplate.setPort(port);
        responseTemplate.setMethod(entry.getRequest().getMethod());
        responseTemplate.setUri(SLASH + context + entry.getRequest().getUri());
        
        responseTemplate.setStatusCode(entry.getResponse().getStatus());
        responseTemplate.setBody(entry.getResponse().getContent().getText());
        
        return responseTemplate;
    }
    
}
