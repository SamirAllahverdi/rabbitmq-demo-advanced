package com.example.controller;

import com.example.entity.DummyMessage;
import com.example.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public String send(){
    producerService.send(new DummyMessage("dummyContent",1));
        return "OK";
    }



}
