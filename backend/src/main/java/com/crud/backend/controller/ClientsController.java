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

    //BY ID
    @GetMapping("/{id}")
    public ClientsModel byId(@PathVariable Long id) throws Exception {

        Optional<ClientsModel> clientOpt = repo.findById(id);
        if(clientOpt.isPresent()) return clientOpt.get();
        else throw new Exception();

    }

    //POST
    @PostMapping
    public ResponseEntity post(@RequestBody ClientsDto dto) throws Exception {

        boolean validate = dto.email().contains("@");

        if(!validate) {
            throw new Exception();
        }

        var newClient = new ClientsModel(dto);
        repo.save(newClient);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {

        Optional<ClientsModel> clientOpt = repo.findById(id);
        if(clientOpt.isPresent()) {
            repo.delete(clientOpt.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new Exception();
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody ClientsDto dto) {

        Optional<ClientsModel> clientOpt = repo.findById(id);
        if(clientOpt.isPresent()) {
            ClientsModel clientUpdate = clientOpt.get();
            clientUpdate.setEmail(dto.email());
            clientUpdate.setPassword(dto.password());

            repo.save(clientUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
