package Springapi.springapi.controller;

import Springapi.springapi.exception.ResourceNotFoundException;
import Springapi.springapi.entity.OwnerModel;
import Springapi.springapi.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owners")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(ownerRepository.findAll());
    }

    @GetMapping("/owners/{id}")
    public ResponseEntity getOwnersById(@PathVariable(value = "id") Long ownerId)
            throws ResourceNotFoundException {
        OwnerModel owner =
                ownerRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Owner not found on :: " + ownerId));
        return ResponseEntity.ok().body(owner);
    }

    @PostMapping("/owners")
    public OwnerModel createOwner(@Valid @RequestBody OwnerModel owner,
                                  @AuthenticationPrincipal Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        return ownerRepository.save(owner);
    }

    @DeleteMapping("/owners/{id}")
    public Map<String, Boolean> deleteOwner(@PathVariable(value = "id") Long ownerId,
                                            @AuthenticationPrincipal Principal principal) throws Exception {
        if (principal == null) {
            throw new ForbiddenException();
        }
        OwnerModel owner =
                ownerRepository
                        .findById(ownerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Owner not found on :: " + ownerId));

        ownerRepository.delete(owner);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
