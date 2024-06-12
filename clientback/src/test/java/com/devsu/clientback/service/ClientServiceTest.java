package com.devsu.clientback.service;

import com.devsu.clientback.entity.Client;
import com.devsu.clientback.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void createTest() {
        Client client = new Client();
        client.setName("test");
        client.setPassword("1234");
        client.setState(true);
        client.setAddress("Calle 123");
        client.setPhone("9877654");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client newObj = clientService.create(client);

        assertThat(newObj).isNotNull();
        assertThat(newObj.getName()).isEqualTo("test");
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}
