package com.github.loki.home;

import com.github.loki.handler.RequestMockHandler;
import com.github.loki.response.GetResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GetResponse getResponse;
    
    @GetMapping
    public ResponseEntity<String> init() throws Exception {

        Integer port = 9898;
        Server server = new Server(port);
        server.setHandler(new RequestMockHandler(port, getResponse));
        
        new Thread(() -> {
            try {
                server.start();
                server.join();
            } catch (Exception ex) {
                log.error("server jetty fail", ex);
            }
        }).start();

        return ResponseEntity.ok("HOME");
    }

    private String getResponse(String target) {

        Map<String, String> responses = new HashMap();

        responses.put("/", "RESPONSE INDEX");
        responses.put("/others", "others response");

        if (responses.containsKey(target)) {
            return responses.get(target);
        } else {
            return "404 - page not found";
        }
    }

    //criar um objeto e colocar dentro do map para representar o response com cabeçalhos e etc
}
