package com.msusuario.controller;

import com.msusuario.entity.Usuario;
import com.msusuario.modelos.Carros;
import com.msusuario.modelos.Motos;
import com.msusuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioControler {
    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> Mostrartodo(){
        List<Usuario> usuarios= service.mostrartodo();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") int id){
        Usuario user= service.buscarpoId(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardar(@RequestBody Usuario usuario){
        Usuario usernew= service.Guardar(usuario);
        return ResponseEntity.ok(usernew);
    }

    @GetMapping("/carros/{userid}")
    public ResponseEntity<List<Carros>> getCarros(@PathVariable("userid") int userid){
        Usuario user= service.buscarpoId(userid);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        List<Carros> carros=service.listaCarros(userid);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/motos/{userid}")
    public ResponseEntity<List<Motos>> getMotos(@PathVariable("userid") int userid){
        Usuario user= service.buscarpoId(userid);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        List<Motos> motos=service.listamotos(userid);
        return ResponseEntity.ok(motos);
    }

    @PostMapping("/guardarcarro/{userid}")
    public ResponseEntity<Carros> guardarcarro(@PathVariable("userid") int userid, @RequestBody Carros carros){
        if(service.buscarpoId(userid) == null){
            return ResponseEntity.notFound().build();
        }
        Carros carnew=service.guardarCarro(userid,carros);
        return ResponseEntity.ok(carnew);
    }

    @PostMapping("/guardarmoto/{userid}")
    public ResponseEntity<Motos> guardarmoto(@PathVariable("userid") int userid, @RequestBody Motos motos){
        if(service.buscarpoId(userid) == null){
            return ResponseEntity.notFound().build();
        }
        Motos motonew=service.guardarMotos(userid,motos);
        return ResponseEntity.ok(motonew);
    }

    @GetMapping("/todo/{userid}")
    public ResponseEntity<Map<String,Object>> mostrarVehiculos(@PathVariable("userid") int userid){
        Map<String,Object> result=service.mostrarUsuariosyVehiculos(userid);
        return ResponseEntity.ok(result);
    }
}
