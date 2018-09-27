package com.github.loki.mock;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Flavio Andrade
 */
@RestController
@RequestMapping("mock")
public class MockController {
    
    private final StartMockServer startMock;
    
    private final ListMockServer list;
    
    private final StopMockServer stop;

    @Autowired
    public MockController(StartMockServer start, ListMockServer list, StopMockServer stop) {
        this.startMock = start;
        this.list = list;
        this.stop = stop;
    }
    
    @PostMapping("/start/port/{port}")
    public void startMockServer(@PathVariable Integer port) {
        
        startMock.start(port);
    }
    
    @GetMapping("/status")
    public Map<Integer, String> listStatusMockServers() {
        
        return list.portStatus();
    }
    
    @PutMapping("/stop/port/{port}")
    public void stopMockServer(@PathVariable Integer port) {
        
    }
}
