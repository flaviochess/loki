package com.github.loki.response;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Flavio Andrade
 */
public interface ResponseConfigRepository extends CrudRepository<ResponseConfig, Long> {
    
    Optional<ResponseConfig> findByPortAndMethodAndUri(Integer port, String method, String uri);
}
