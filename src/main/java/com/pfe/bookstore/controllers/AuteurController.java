package com.pfe.bookstore.controllers;

import com.pfe.bookstore.DTO.AuteurDTO;
import com.pfe.bookstore.DTO.AuteurDTO1;
import com.pfe.bookstore.DTO.ClientDTO;
import com.pfe.bookstore.services.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auteur")
@CrossOrigin("*")
public class AuteurController {

    @Autowired
    private AuteurService auteurService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getAuteur(@PathVariable Long id){
        AuteurDTO1 auteur = auteurService.getAuteur(id);
        if(auteur!=null)
            return ResponseEntity.ok(auteur);
        return ResponseEntity.ok("Author Not Found");
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAuteurByPage(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok()
                .body(auteurService.getAuteurs(page));
    }
    @GetMapping("/top")
    public ResponseEntity<?> getTopAuteurs(){
        return ResponseEntity.ok()
                .body(auteurService.getTopAuteurs());
    }
    @PutMapping("")
    public  ResponseEntity<?> updateClient(@RequestBody AuteurDTO auteurDTO){
        return ResponseEntity.ok(auteurService.updateAuteur(auteurDTO));
    }

}
