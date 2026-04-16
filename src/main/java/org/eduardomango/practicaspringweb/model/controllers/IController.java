package org.eduardomango.practicaspringweb.model.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IController<T> {
    @GetMapping
    ResponseEntity<T> ReturnEntities();
    @GetMapping("/{id}")
    ResponseEntity<T> ReturnEntity(@PathVariable long id);
    @PostMapping
    ResponseEntity<T> CreateEntity(@RequestBody T entity);
}
