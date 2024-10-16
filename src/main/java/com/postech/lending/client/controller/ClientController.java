package com.postech.lending.client.controller;

import com.postech.lending.client.dto.ClientDTO;
import com.postech.lending.client.service.ClientService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> allClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> clientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.clientById(id));
    }

    @GetMapping("/document")
    public ResponseEntity<List<ClientDTO>> allClientsByDocument(@Param("document") String document) {
        return ResponseEntity.ok(clientService.clientsByDocument(document));
    }

    @GetMapping("/profile")
    public ResponseEntity<List<ClientDTO>> clientsProfile() {
        return ResponseEntity.ok(clientService.clientStatusProfileDisabled());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> newClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO newClient = clientService.createNewClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> clientUpdate(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.clientUpdate(id, clientDTO));
    }

    @PostMapping("disabled/{id}")
    public ResponseEntity<ClientDTO> disableClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.disableClient(id));
    }

}
