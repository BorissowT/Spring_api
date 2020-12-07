package Springapi.springapi.controller;

import Springapi.springapi.entity.AuthorModel;
import Springapi.springapi.exception.ResourceNotFoundException;
import Springapi.springapi.kafka.KafkaProducer;
import Springapi.springapi.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class AuthorController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "TEST1";
    
    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public ResponseEntity<Object> getAllAuthors() {
        return ResponseEntity.ok(authorRepository.findAll());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity getAuthorById(@PathVariable(value = "id") Long authorId)
            throws ResourceNotFoundException {
        
        AuthorModel author =
                authorRepository
                        .findById(authorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found on :: " + authorId));             
        return ResponseEntity.ok().body(author);
    }

    @PostMapping("/authors")
    public AuthorModel createAuthor(@Valid @RequestBody AuthorModel author,
                                    @AuthenticationPrincipal Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        sendMessage("created autor: " + author.getFNAME() + " " + author.getLNAME());
        AuthorModel savedAuthor = authorRepository.save(author);
        try{
            kafkaProducer.sendMessage(author);
        }
        catch(Exception e){
            return null;            // Always must return something
        }
        return savedAuthor;
    }

    @DeleteMapping("/authors/{id}")
    public Map<String, Boolean> deleteAuthor(@PathVariable(value = "id") Long authorId,
                                             @AuthenticationPrincipal Principal principal) throws Exception {
        if (principal == null) {
            throw new ForbiddenException();
        }
        AuthorModel author =
                authorRepository
                        .findById(authorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Author not found on :: " + authorId));

        authorRepository.delete(author);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send(TOPIC, msg);
    }

}
