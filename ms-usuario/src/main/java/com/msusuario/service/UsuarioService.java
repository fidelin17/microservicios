package com.msusuario.service;

import com.msusuario.entity.Usuario;
import com.msusuario.feigClients.CarFeingClients;
import com.msusuario.feigClients.MotoFeingClients;
import com.msusuario.modelos.Carros;
import com.msusuario.modelos.Motos;
import com.msusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeingClients carFeingClients;

    @Autowired
    MotoFeingClients motoFeingClients;



    public List<Usuario> mostrartodo(){
        return  repository.findAll();
    }

    public Usuario buscarpoId(int id){
        return repository.findById(id).orElse(null);
    }

    public Usuario Guardar(Usuario usuario){
        Usuario usuarionew= repository.save(usuario);
        return  usuarionew;
    }

    public List<Carros> listaCarros(int userid){
        List<Carros> carros=restTemplate.getForObject("http://ms-carro/carro/byuser/"+userid,List.class); //http://localhost:8082/carro/byuser/
        return carros;
    }

    public List<Motos> listamotos(int userid){
        List<Motos> motos=restTemplate.getForObject("http://ms-moto/moto/byuser/"+userid,List.class); //http://localhost:8083/moto/byuser/
        return motos;

    }

    public Carros guardarCarro(int userid,Carros carros){
        carros.setUserid(userid);
        Carros carnew= carFeingClients.guardar(carros);
        return carnew;

    }

    public Motos guardarMotos(int userid,Motos motos){
       motos.setUserid(userid);
        Motos motonew= motoFeingClients.guardar(motos);
        return motonew;
    }

    public Map<String, Object> mostrarUsuariosyVehiculos(int userid){
        Map<String,Object> resul= new HashMap<>();
        Usuario usuario =repository.findById(userid).orElse(null);
        if(usuario==null){
            resul.put("Mensaje","no existe el usuario");
            return resul;
        }else{
            resul.put("user",usuario);
            List<Carros> cars=carFeingClients.getCarros(userid);
            if(cars.isEmpty()){
                resul.put("Carro","el usuario no tiene coches");
            }else{
                resul.put("Carro",cars);
            }
            List<Motos> motos=motoFeingClients.getMotos(userid);
            if(motos.isEmpty()){
                resul.put("Moto","el usuario no tiene motos");
            }else{
                resul.put("Moto",motos);
            }
            return resul;
        }

    }


}

