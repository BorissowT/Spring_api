package Springapi.springapi.controller;

import Springapi.springapi.entity.OwnerModel;
import Springapi.springapi.entity.StoreModel;
import Springapi.springapi.exception.ResourceNotFoundException;
import Springapi.springapi.repository.StoreRepository;
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

public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/stores")
    public ResponseEntity<Object> getAllStores() {
        return ResponseEntity.ok(storeRepository.findAll());
    }

    @GetMapping("/stores/{id}")
    public ResponseEntity getStoresById(@PathVariable(value = "id") Long storeId)
            throws ResourceNotFoundException {
        StoreModel store =
                storeRepository
                        .findById(storeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Store not found on :: " + storeId));
        return ResponseEntity.ok().body(store);
    }

    @PostMapping("/stores")
    public StoreModel createStore(@Valid @RequestBody StoreModel store,
                                  @AuthenticationPrincipal Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }
        return storeRepository.save(store);
    }

    @DeleteMapping("/stores/{id}")
    public Map<String, Boolean> deleteStore(@PathVariable(value = "id") Long storeId,
                                            @AuthenticationPrincipal Principal principal) throws Exception {
        if (principal == null) {
            throw new ForbiddenException();
        }
        StoreModel store =
                storeRepository
                        .findById(storeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Store not found on :: " + storeId));

        storeRepository.delete(store);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
