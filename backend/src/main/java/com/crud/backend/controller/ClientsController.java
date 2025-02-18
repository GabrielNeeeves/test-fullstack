package com.crud.backend.controller;

import com.crud.backend.model.ClientsDto;
import com.crud.backend.model.ClientsModel;
import com.crud.backend.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsRepository repo;

    //GET
    @GetMapping
    public List<ClientsModel> get() {
        return repo.findAll();
    }

    //POST
    @PostMapping
    public ResponseEntity<String> post(@RequestBody ClientsDto dto) {

//        try {

            var newClient = new ClientsModel(dto);
            repo.save(newClient);
            return new ResponseEntity(HttpStatus.CREATED);

//        } catch(Exception e) {
//            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
//        }

    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        Optional<ClientsModel> clientOpt = repo.findById(id);
        if(clientOpt.isPresent()) {
            repo.delete(clientOpt.get());
            return new ResponseEntity<>("Usuário deletado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<String> put(@PathVariable Long id, @RequestBody ClientsDto dto) {

        Optional<ClientsModel> clientOpt = repo.findById(id);
        if(clientOpt.isPresent()) {
            ClientsModel clientUpdate = clientOpt.get();
            clientUpdate.setEmail(dto.email());
            clientUpdate.setPassword(dto.password());

            repo.save(clientUpdate);
            return new ResponseEntity<>("Usuário atualizado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
