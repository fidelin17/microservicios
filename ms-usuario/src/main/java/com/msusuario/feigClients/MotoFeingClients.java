package com.msusuario.feigClients;

import com.msusuario.modelos.Motos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-moto") //url = "http://localhost:8083/moto")
public interface MotoFeingClients {

    @PostMapping()
    Motos guardar(@RequestBody Motos motos);

    @GetMapping("byuser/{userid}")
    List<Motos> getMotos(@PathVariable("userid") int userid);
}
