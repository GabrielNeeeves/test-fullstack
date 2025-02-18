package com.crud.backend.controller;

import com.crud.backend.model.ClientsDto;
import com.crud.backend.model.ClientsModel;
import com.crud.backend.repository.ClientsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class ClientsControllerTestIT {

    @Autowired
    private ClientsController clientsController;

    @Autowired
    private ClientsRepository clientsRepository;

    @Test
    @DisplayName("Verifica se o último registro condiz com a inserção")
    public void deveRetornarListaDeClientes() throws Exception {

        ClientsDto dto = new ClientsDto("gggbbb@.com", "222");
        clientsController.post(dto);

        List<ClientsModel> clientes = clientsRepository.findAll();

        ClientsModel ultimoRegistro = clientes.get(clientes.size()-1);

        Assertions.assertEquals("gggbbb@.com", ultimoRegistro.getEmail());
    }

    @Test
    public void deveLancarExceptionSeIdNaoExistir() {

        Assertions.assertThrows(Exception.class, () -> clientsController.byId(99L));
    }

}
