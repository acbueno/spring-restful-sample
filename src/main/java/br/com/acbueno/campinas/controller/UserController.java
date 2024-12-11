package br.com.acbueno.campinas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Users", description = "Endpoints for managing users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  @Operation(summary = "Get all Users", description = "List all users")
  @Cacheable("user-cache")
  public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
    Page<UserResponseDTO> users = userService.findAll(pageable);
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get Bike by ID", description = "Retrieve a bike using its unique ID")
  public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("id") Long id) {
    UserResponseDTO response = userService.findUserById(id);
    if (response == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(response);
  }

  @PostMapping
  @Operation(summary = "Persist new user", description = "Add new user")
  @CacheEvict(value = "user-cache", allEntries = true)
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete user", description = "Delete a user by id")
  @CacheEvict(value = "user-cache", allEntries = true)
  public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
    try {
      userService.deleteUserById(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }

  }

  @PutMapping("/{id}")
  @Operation(summary = "Update car", description = "Update a car by id")
  @CacheEvict(value = "user-cache", allEntries = true)
  public ResponseEntity<UserResponseDTO> userUpdateById(@PathVariable("id") Long id,
      @RequestBody UserRequestDTO request) {
    try {
      UserResponseDTO response = userService.updateUserById(id, request);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
  }

}
