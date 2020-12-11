package Springapi.springapi.controller;

import Springapi.springapi.entity.ClientModel;
import Springapi.springapi.exception.ResourceNotFoundException;
import Springapi.springapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public ResponseEntity<Object> getAllClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity getClientById(@PathVariable(value = "id") Long clientId)
            throws ResourceNotFoundException {
        ClientModel client =
                clientRepository
                        .findById(clientId)
                        .orElseThrow(() -> new ResourceNotFoundException("Client not found on :: " + clientId));
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/clients")
    public ClientModel createClient(@Valid @RequestBody ClientModel client,
                                    @AuthenticationPrincipal Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        return clientRepository.save(client);
    }

    @DeleteMapping("/clients/{id}")
    public Map<String, Boolean> deleteClient(@PathVariable(value = "id") Long clientId,
                                             @AuthenticationPrincipal Principal principal) throws Exception {
        if (principal == null) {
            throw new ForbiddenException();
        }
        ClientModel client =
                clientRepository
                        .findById(clientId)
                        .orElseThrow(() -> new ResourceNotFoundException("Client not found on :: " + clientId));

        clientRepository.delete(client);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
