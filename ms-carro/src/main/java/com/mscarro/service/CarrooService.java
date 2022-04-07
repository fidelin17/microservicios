package com.mscarro.service;

import com.mscarro.entity.Carroo;
import com.mscarro.repository.CarrooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrooService {
    @Autowired
    CarrooRepository repository;

    public List<Carroo> mostrarTodo(){
        return  repository.findAll();
    }
    public Carroo buscarporId(int id){
        return repository.findById(id).orElse(null);
    }
    public Carroo guardar(Carroo carroo){
        Carroo carroo1=repository.save(carroo);
        return carroo1;
    }

    public List<Carroo> byUserid(int userid){
        return repository.findByUserid(userid);
    }
}
