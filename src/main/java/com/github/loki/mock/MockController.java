package com.github.loki.mock;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Flavio Andrade
 */
@RestController
@RequestMapping("mock")
public class MockController {
    
    private final StartMockServer startMockServer;
    
    private final ListMockServer listMockServer;
    
    private final StopMockServer stopMockServer;

    @Autowired
    public MockController(StartMockServer startMockServer, ListMockServer listMockServer, StopMockServer stopMockServer) {
        this.startMockServer = startMockServer;
        this.listMockServer = listMockServer;
        this.stopMockServer = stopMockServer;
    }
    
    @PostMapping("/port/{port}/start")
    public void startMockServer(@PathVariable Integer port) {
        
        startMockServer.start(port);
    }
    
    @GetMapping("/status")
    public Map<Integer, String> listStatusMockServers() {
        
        return listMockServer.portStatus();
    }
    
    @PostMapping("/port/{port}/stop")
    public void stopMockServer(@PathVariable Integer port) {
        
        stopMockServer.fromPort(port);
    }
}
