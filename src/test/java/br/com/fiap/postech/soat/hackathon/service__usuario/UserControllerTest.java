package br.com.fiap.postech.soat.hackathon.service__usuario;

import br.com.fiap.postech.soat.hackathon.service__usuario.adapters.controller.UserController;
import br.com.fiap.postech.soat.hackathon.service__usuario.application.port.UserService;
import br.com.fiap.postech.soat.hackathon.service__usuario.adapters.records.UserRecord;
import br.com.fiap.postech.soat.hackathon.service__usuario.domain.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.fiap.postech.soat.hackathon.service__usuario.domain.enums.TipoUsuario.MEDICO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testCreateUser() {
        UserRecord userRecord = new UserRecord(1L, MEDICO, "John", "34234423", "john@example.com", "3344331");
        when(userService.createUser(any(UserRecord.class))).thenReturn(userRecord);

        ResponseEntity<UserRecord> response = userController.createUser(userRecord);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userRecord, response.getBody());
    }

    @Test
    void testUpdateUser() throws UserNotFoundException {
        UserRecord userRecord = new UserRecord(1L, MEDICO, "John", "34234423", "john@example.com", "3344331");
        when(userService.updateUser(anyLong(), any(UserRecord.class))).thenReturn(userRecord);

        ResponseEntity<UserRecord> response = userController.updateUser(1L, userRecord);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userRecord, response.getBody());
    }

    @Test
    void testDeleteUser() {
        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetAllUsers() throws UserNotFoundException {
        List<UserRecord> users = Arrays.asList(
                new UserRecord(1L, MEDICO, "John", "34234423", "john@example.com", "76556"),
                new UserRecord(1L, MEDICO, "John", "34234423", "john@example.com", "342432")
        );
        when(userService.findAllUsers()).thenReturn(users);

        ResponseEntity<List<UserRecord>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testGetUserById_Success() throws UserNotFoundException {
        UserRecord userRecord = new UserRecord(1L, MEDICO, "John", "34234423", "john@example.com", "76556");
        when(userService.findUserById(1L)).thenReturn(userRecord);

        ResponseEntity<UserRecord> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userRecord, response.getBody());
    }

    @Test
    void testGetUserById_NotFound() throws UserNotFoundException {
        when(userService.findUserById(1L)).thenThrow(new UserNotFoundException());

        ResponseEntity<UserRecord> response = userController.getUserById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetUserByCrm_Success() throws UserNotFoundException {
        UserRecord userRecord = new UserRecord(1L, MEDICO, "John", "34234423", "john@example.com", "76556");
        when(userService.findUserByCrm("12345")).thenReturn(userRecord);

        ResponseEntity<UserRecord> response = userController.getUserByCrm("12345");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userRecord, response.getBody());
    }

    @Test
    void testGetUserByCrm_NotFound() throws UserNotFoundException {
        when(userService.findUserByCrm("12345")).thenThrow(new UserNotFoundException());

        ResponseEntity<UserRecord> response = userController.getUserByCrm("12345");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetUserByDocumento_Success() throws UserNotFoundException {
        UserRecord userRecord = new UserRecord(1L, MEDICO, "John", "34234423", "john@example.com", "76556");
        when(userService.findUserByDocumento("123456789")).thenReturn(userRecord);

        ResponseEntity<UserRecord> response = userController.getUserByDocumento("123456789");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userRecord, response.getBody());
    }

    @Test
    void testGetUserByDocumento_NotFound() throws UserNotFoundException {
        when(userService.findUserByDocumento("123456789")).thenThrow(new UserNotFoundException());

        ResponseEntity<UserRecord> response = userController.getUserByDocumento("123456789");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
