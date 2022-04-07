package com.mscarro.controller;

import com.mscarro.entity.Carroo;
import com.mscarro.service.CarrooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarrooController {
    @Autowired
    CarrooService service;

    @GetMapping
    public ResponseEntity<List<Carroo>> mostrartodo() {
        List<Carroo> carroos = service.mostrarTodo();
        if (carroos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carroos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carroo> buscarpoid(@PathVariable("id") int id) {
        Carroo car = service.buscarporId(id);
        if (car == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Carroo> guardar(@RequestBody Carroo carroo) {
        Carroo carronew = service.guardar(carroo);
        return ResponseEntity.ok(carronew);
    }

    @GetMapping("byuser/{userid}")
    public ResponseEntity<List<Carroo>> mostrarUSErByid(@PathVariable("userid") int userid) {
        List<Carroo> carroos = service.byUserid(userid);

        return ResponseEntity.ok(carroos);


    }

}
