package org.eduardomango.practicaspringweb.model.controllers;

import lombok.AllArgsConstructor;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Service
public class ProductController implements IController<ProductEntity>{

    private ProductService productService;


    @Override
    public ResponseEntity<ProductEntity> ReturnEntities() {
        return null;
    }

    @Override
    public ResponseEntity<ProductEntity> ReturnEntity(long id) {
        return null;
    }

    @Override
    public ResponseEntity<ProductEntity> CreateEntity(@RequestBody ProductEntity entity) {
        return ResponseEntity.ok().build();
    }
}
