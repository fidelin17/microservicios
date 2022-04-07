package com.msmoto.controller;

import com.msmoto.entity.Moto;
import com.msmoto.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {
    
    @Autowired
    MotoService service;

    @GetMapping
    public ResponseEntity<List<Moto>> mostrartodo(){
        List<Moto> motos= service.mostrarTodo();
        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> buscarpoid(@PathVariable("id") int id) {
        Moto moto = service.buscarporId(id);
        if (moto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> guardar(@RequestBody Moto moto) {
        Moto motonew = service.guardar(moto);
        return ResponseEntity.ok(motonew);
    }

    @GetMapping("byuser/{userid}")
    public ResponseEntity<List<Moto>> mostrarUSErByid(@PathVariable("userid") int userid) {
        List<Moto> motos = service.byUserid(userid);
        return ResponseEntity.ok(motos);


    }
}
