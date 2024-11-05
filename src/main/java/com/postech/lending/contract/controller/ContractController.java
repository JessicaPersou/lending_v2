package com.postech.lending.contract.controller;

import com.postech.lending.contract.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    private final ContractService contractService;

    public ContractController (ContractService contractService){
        this.contractService = contractService;
    }

    @GetMapping("/{clientId}/generate")
    public ResponseEntity<String> generateContract(@PathVariable Long clientId) {
        String result = contractService.generateContract(clientId);
        return ResponseEntity.ok(result);
    }
}
