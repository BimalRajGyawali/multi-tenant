package com.personal.multidb.controller;

import com.personal.multidb.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bimal on 10/4/21
 * @project multi-db
 */
@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts(@RequestParam("clientId") int clientId){
        return ResponseEntity.ok(clientService.getAccounts(clientId));
    }
}
