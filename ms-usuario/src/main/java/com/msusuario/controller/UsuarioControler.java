package com.msusuario.controller;

import com.msusuario.entity.Usuario;
import com.msusuario.modelos.Carros;
import com.msusuario.modelos.Motos;
import com.msusuario.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    //carro--------------------------------------------------------------------------
    //entra al micro ms-carros para obtener los carros registrados con el userid
    @CircuitBreaker(name = "carroCB", fallbackMethod = "obtenerCarrosCB")
    @GetMapping("/carros/{userid}")
    public ResponseEntity<List<Carros>> getCarros(@PathVariable("userid") int userid){
        Usuario user= service.buscarpoId(userid);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        List<Carros> carros=service.listaCarros(userid);
        return ResponseEntity.ok(carros);
    }
    //guarda un carro con el dato del userid
    @CircuitBreaker(name = "carroCB", fallbackMethod = "guardarCarroCB")
    @PostMapping("/guardarcarro/{userid}")
    public ResponseEntity<Carros> guardarcarro(@PathVariable("userid") int userid, @RequestBody Carros carros){
        if(service.buscarpoId(userid) == null){
            return ResponseEntity.notFound().build();
        }
        Carros carnew=service.guardarCarro(userid,carros);
        return ResponseEntity.ok(carnew);
    }
    //moto--------------------------------------------------------------------------
    @CircuitBreaker(name = "motoCB", fallbackMethod = "obtenerMotosCB")
    @GetMapping("/motos/{userid}")
    public ResponseEntity<List<Motos>> getMotos(@PathVariable("userid") int userid){
        Usuario user= service.buscarpoId(userid);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        List<Motos> motos=service.listamotos(userid);
        return ResponseEntity.ok(motos);
    }
    @CircuitBreaker(name = "motoCB", fallbackMethod = "guardarMotoCB")
    @PostMapping("/guardarmoto/{userid}")
    public ResponseEntity<Motos> guardarmoto(@PathVariable("userid") int userid, @RequestBody Motos motos){
        if(service.buscarpoId(userid) == null){
            return ResponseEntity.notFound().build();
        }
        Motos motonew=service.guardarMotos(userid,motos);
        return ResponseEntity.ok(motonew);
    }

    //toddo
    //obtiene todos los carros y motos que tiene el userid
    @CircuitBreaker(name = "todoCB", fallbackMethod = "mostrartodoCB")
    @GetMapping("/todo/{userid}")
    public ResponseEntity<Map<String,Object>> mostrarVehiculos(@PathVariable("userid") int userid){
        Map<String,Object> result=service.mostrarUsuariosyVehiculos(userid);
        return ResponseEntity.ok(result);
    }
        //carros---------------------------------------------------------------------------------------------
    private ResponseEntity<List<Carros>> obtenerCarrosCB(@PathVariable("userid") int userid , RuntimeException e){
        return new  ResponseEntity("el usuario "+userid+" tien los carros en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Carros> guardarCarroCB(@PathVariable("userid") int userid, @RequestBody Carros carros , RuntimeException e){
        return new  ResponseEntity("el usuario "+userid+" no tiene dinero para comprar carros", HttpStatus.OK);
    }
        //motos----------------------------------------------------------------------------------------------
    private ResponseEntity<List<Motos>> obtenerMotosCB(@PathVariable("userid") int userid , RuntimeException e){
        return new  ResponseEntity("el usuario "+userid+" tien las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<Motos> guardarMotoCB(@PathVariable("userid") int userid, @RequestBody Motos motos , RuntimeException e){
        return new  ResponseEntity("el usuario "+userid+" no tiene dinero para comprar motos", HttpStatus.OK);
    }
    //todoo
    private ResponseEntity<Map<String,Object>> mostrartodoCB(@PathVariable("userid") int userid, RuntimeException e){
        return new  ResponseEntity("el usuario "+userid+" tien las vehiculos en el taller", HttpStatus.OK);
    }
}
