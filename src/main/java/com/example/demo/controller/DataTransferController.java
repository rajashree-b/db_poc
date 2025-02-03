package com.example.demo.controller;

import com.example.demo.service.DataTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class DataTransferController {

    @Autowired
    private DataTransferService dataTransformationService;

    @GetMapping("/transform")
    public ResponseEntity<String> transformData() {
        dataTransformationService.transformAndInsertData();
        return ResponseEntity.ok("Data transformation and insertion completed!");
    }
}

