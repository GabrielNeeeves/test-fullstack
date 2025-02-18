package com.crud.backend.repository;

import com.crud.backend.model.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository  extends JpaRepository<ClientsModel, Long> {
}
