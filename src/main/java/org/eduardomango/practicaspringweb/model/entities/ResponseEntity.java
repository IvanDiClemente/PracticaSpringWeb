package org.eduardomango.practicaspringweb.model.entities;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ResponseEntity<T>{
}
