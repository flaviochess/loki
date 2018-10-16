/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * Class responsible for reading configuration files
 *
 * @author Flavio Andrade
 */
@Slf4j
@Component
public class GetMockFilesConfigurations {
    
    private static final String SLASH = "/";
    
    @Value("${loki.path.configuration.files}")
    private String pathConfigurationFiles;

    /**
     * Reads configuration files and convert to ResponseTemplate object.
     *
     * @return the settings list within the configuration files converted to
     * ResponseTemplate list.
     */
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
    
    //TODO: será que mantem a ordem do arquivo? É importante manter a ordem de configuração do arquivo
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
