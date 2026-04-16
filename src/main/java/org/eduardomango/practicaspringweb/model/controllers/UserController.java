package org.eduardomango.practicaspringweb.model.controllers;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.eduardomango.practicaspringweb.model.exceptions.UserNotFoundException;
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
        try{
            if(userService.findById(user.getId())==null){
                userService.save(user);
                return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }catch(UserNotFoundException e){
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> actualizarUsuario(@PathVariable long id, @RequestBody UserEntity user){
        try{
            if(userService.findById(id)!=null){
                userService.update(user);
                return ResponseEntity.ok().body(user);
            }else{
                throw new UserNotFoundException();
            }
        }catch(UserNotFoundException e){
            System.out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserEntity> eliminarUsuario(@PathVariable long id){
        try{
            userService.delete(userService.findById(id));
            return ResponseEntity.noContent().build();
        }catch(UserNotFoundException e){
            System.out.println("An error has occurred: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
}
