package org.eduardomango.practicaspringweb.model.controllers;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Service
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> retornarTodosLosUsuarios(){

        if(userService.findAll()!=null){
            return ResponseEntity.ok(userService.findAll());
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> retornarUsuarios(@PathVariable long id){

        if(userService.findById(id)!=null){
            return ResponseEntity.ok(userService.findById(id));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping
    public ResponseEntity<UserEntity> crearUsuario(@RequestBody UserEntity user){
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public void actualizarUsuario(@PathVariable long id, @RequestBody UserEntity user){
        System.out.println(user);
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable long id){
        userService.delete(userService.findById(id));
    }

}
