package com.crud.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class ClientsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    public ClientsModel(){}

    public ClientsModel(ClientsDto dto) {
        email = dto.email();
        password = dto.password();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
