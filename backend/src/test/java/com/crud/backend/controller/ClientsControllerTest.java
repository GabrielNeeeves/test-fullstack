package com.crud.backend.controller;

import com.crud.backend.model.ClientsDto;
import com.crud.backend.model.ClientsModel;
import com.crud.backend.repository.ClientsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ClientsControllerTest {

    @InjectMocks
    private ClientsController clientsController;

    @Mock
    private ClientsRepository clientsRepository;

    @Test
    public void shouldReturnListWithOneClient() {

        ClientsDto client = new ClientsDto("teste@gmail.com", "123");
        Mockito.when(clientsRepository.findAll()).thenReturn(Collections.singletonList(new ClientsModel(client)));
        List<ClientsModel> clientes = clientsController.get();

        Assertions.assertEquals(1, clientes.size());
    }

    @Test
    @DisplayName("Deve lançar Exception se email não ter @")
    public void deveLancarExcecaoQuandoEmailInvalido() {

        ClientsDto client = new ClientsDto("teste.com", "123");
        Assertions.assertThrows(Exception.class, () -> clientsController.post(client));
    }

}
