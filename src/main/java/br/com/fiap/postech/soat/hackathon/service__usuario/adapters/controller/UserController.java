package br.com.fiap.postech.soat.hackathon.service__usuario.adapters.controller;

import br.com.fiap.postech.soat.hackathon.service__usuario.adapters.records.UserRecord;
import br.com.fiap.postech.soat.hackathon.service__usuario.application.port.UserService;
import br.com.fiap.postech.soat.hackathon.service__usuario.domain.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastrar")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/usuario")
    public ResponseEntity<UserRecord> createUser(@RequestBody UserRecord userRecord) {
        UserRecord createdUser = userService.createUser(userRecord);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<UserRecord> updateUser(@PathVariable Long id, @RequestBody UserRecord userRecord) throws UserNotFoundException {
        UserRecord updatedUser = userService.updateUser(id, userRecord);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/usuario/medico/{id}")
    public ResponseEntity<UserRecord> updateUserMedico(@PathVariable Long id, @RequestBody UserRecord userRecord) throws UserNotFoundException {
        UserRecord updatedUser = userService.updateUserMedico(id, userRecord);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<UserRecord>> getAllUsers() {
        try {
            List<UserRecord> users = userService.findAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<UserRecord> getUserById(@PathVariable Long id) {
        try {
            UserRecord user = userService.findUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/crm/{crm}")
    public ResponseEntity<UserRecord> getUserByCrm(@PathVariable String crm) {
        try {
            UserRecord user = userService.findUserByCrm(crm);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/documento/{documento}")
    public ResponseEntity<UserRecord> getUserByDocumento(@PathVariable String documento) {
        try {
            UserRecord user = userService.findUserByDocumento(documento);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
