package org.eduardomango.practicaspringweb.model.controllers;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.apache.catalina.User;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.services.SaleService;
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
    public List<UserEntity> retornarTodosLosUsuarios(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity retornarUsuarios(@PathVariable long id){
        return userService.findById(id);
    }

    @PostMapping
    public void crearUsuario(@RequestBody UserEntity user){
        userService.save(user);
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
