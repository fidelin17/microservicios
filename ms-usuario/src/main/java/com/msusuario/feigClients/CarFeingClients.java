package com.msusuario.feigClients;

import com.msusuario.modelos.Carros;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-carro")//url = "http://localhost:8082/carro")
@RequestMapping("/carro")
public interface CarFeingClients {

    @PostMapping()
    Carros guardar(@RequestBody Carros carro);

    @GetMapping("byuser/{userid}")
    List<Carros> getCarros(@PathVariable("userid") int userid);




}
