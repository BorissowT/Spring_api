/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Springapi.springapi.kafka;

import Springapi.springapi.entity.AuthorModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author wildhost5
 */
@Service
@Log4j
public class KafkaProducer {    
    
    private static final String TOPIC = "TEST4";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(AuthorModel authorModel) throws JsonProcessingException {
        log.info(String.format("#### -> Producing message -> %s", authorModel.toString()));
        ObjectMapper mapper = new ObjectMapper();
        String message  = mapper.writeValueAsString(authorModel);
        this.kafkaTemplate.send(TOPIC, message);
    }

}
