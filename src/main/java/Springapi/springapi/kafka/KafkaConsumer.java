/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Springapi.springapi.kafka;

import Springapi.springapi.entity.AuthorModel;
import Springapi.springapi.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.log4j.Log4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author wildhost5
 */
@Service
@Log4j
public class KafkaConsumer {

    @Autowired
    AuthorRepository authorRepository;

    @KafkaListener(topics = "TEST2", groupId = "group0")
    public void consume(ConsumerRecord<String,String> message) throws IOException {
        System.out.print(String.format("#### -> Consumed message -> %s", message));
        log.info(String.format("#### -> Consumed message -> %s", message));
        ObjectMapper mapper = new ObjectMapper();
        AuthorModel authorModel = mapper.readValue(message.value(), AuthorModel.class);
        authorRepository.save(authorModel);
    }

}
