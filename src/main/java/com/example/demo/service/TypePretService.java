package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.TypePret;
import com.example.demo.repository.TypePretRepository;

import java.util.List;

@Service
public class TypePretService {
    
    @Autowired
    private TypePretRepository typePretRepository;
    
    public List<TypePret> findAllTypePret() {
        return typePretRepository.findAll();
    }
}