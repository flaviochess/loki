package com.github.loki.response;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Flavio Andrade
 */
public interface ResponseTemplateRepository extends CrudRepository<ResponseTemplate, Long> {
    
    Optional<ResponseTemplate> findByPortAndMethodAndUri(Integer port, String method, String uri);
    
    List<ResponseTemplate> findByPortAndMethodOrderByIdAsc(Integer port, String method);
    
    @Query("SELECT DISTINCT port FROM ResponseTemplate")
    Set<Integer> listAllPorts();
}
