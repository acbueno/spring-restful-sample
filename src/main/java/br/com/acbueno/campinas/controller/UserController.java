package br.com.acbueno.campinas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.acbueno.campinas.dto.UserRequestDTO;
import br.com.acbueno.campinas.dto.UserResponseDTO;
import br.com.acbueno.campinas.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(userService.findUserById(id));
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
    return ResponseEntity.ok(userService.save(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
    userService.deleteUserById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<UserResponseDTO> userUpdateById(@PathVariable("id") Long id,
      @RequestBody UserRequestDTO request) {
    return ResponseEntity.ok(userService.updateUserById(id, request));
  }

}
