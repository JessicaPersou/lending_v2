package com.postech.lending.client.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.postech.lending.client.dto.ClientDTO;
import com.postech.lending.client.model.Client;
import com.postech.lending.client.model.enums.ProfileState;
import com.postech.lending.client.model.enums.UserRole;
import com.postech.lending.client.repository.ClientRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Inicializa os mocks automaticamente
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void testCreateNewClient() {
        // Dados de entrada (ClientDTO)
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("John");
        clientDTO.setLastName("Doe");
        clientDTO.setBirthdate(LocalDate.of(1990, 1, 1));
        clientDTO.setDocument("123456789");
        clientDTO.setEmail("john.doe@example.com");
        clientDTO.setPhone("123456789");
        clientDTO.setUserRole(UserRole.valueOf("USER"));

        // Mock da entidade Client para salvar
        Client savedClient = new Client();
        savedClient.setId(1L);  // Simulando que o banco gerou o ID 1
        savedClient.setFirstName("John");
        savedClient.setLastName("Doe");
        savedClient.setBirthdate(LocalDate.of(1990, 1, 1));
        savedClient.setDocument("123456789");
        savedClient.setEmail("john.doe@example.com");
        savedClient.setPhone("123456789");
        savedClient.setDtCreated(LocalDate.now());
        savedClient.setUserRole(UserRole.valueOf("USER"));
        savedClient.setProfileState(ProfileState.ACTIVE);

        // Configura o comportamento do mock para que ele retorne o cliente com ID
        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        // Executa o método que está sendo testado
        ClientDTO result = clientService.createNewClient(clientDTO);

        // Verifica se o cliente foi salvo corretamente
        assertNotNull(result);
        assertEquals(1L, result.getId());  // Verifica se o ID gerado é 1
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("123456789", result.getDocument());

        // Verifica se o método save do repository foi chamado
        verify(clientRepository, times(1)).save(any(Client.class));
    }
}
