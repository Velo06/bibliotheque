package com.example.demo.controller;

import com.example.demo.entity.Adherent;
import com.example.demo.service.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/adherents")
public class AdherentRestController {

    @Autowired
    private AdherentService adherentService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAdherentDetails(@PathVariable("id") Long id) {
        return ResponseEntity.ok(adherentService.getAdherentDetails(id));
    }
}