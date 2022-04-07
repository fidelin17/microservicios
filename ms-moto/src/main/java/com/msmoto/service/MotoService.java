package com.msmoto.service;

import com.msmoto.entity.Moto;
import com.msmoto.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    MotoRepository repository;

    public List<Moto> mostrarTodo(){
        return  repository.findAll();
    }
    public Moto buscarporId(int id){
        return repository.findById(id).orElse(null);
    }
    public Moto guardar(Moto moto){
        Moto motonew=repository.save(moto);
        return motonew;
    }

    public List<Moto> byUserid(int userid){
        return repository.findByUserid(userid);
    }
}
