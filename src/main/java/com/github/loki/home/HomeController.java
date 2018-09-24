package com.github.loki.home;

import com.github.loki.server.CreateNewServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Flavio Andrade
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> init() throws Exception {

        new Thread(() -> {
            try {
                CreateNewServer.create(9898);
            } catch (Exception ex) {
                log.error("server jetty fail", ex);
            }
        }).start();

        return ResponseEntity.ok("HOME");
    }
}
