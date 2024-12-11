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
import br.com.acbueno.campinas.dto.BikeRequestDTO;
import br.com.acbueno.campinas.dto.BikeResponseDTO;
import br.com.acbueno.campinas.service.BikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/bikes")
@Tag(name = "Bikes", description = "Endpoints for managing bikes")
public class BikeController {

  @Autowired
  private BikeService bikeService;

  @GetMapping
  @Operation(summary = "Get all Bike", description = "List all bikes")
  @Cacheable("bike-cache")
  public ResponseEntity<Page<BikeResponseDTO>> getAllBikes(Pageable pageable) {
    Page<BikeResponseDTO> bikes = bikeService.findAll(pageable);
    return ResponseEntity.ok(bikes);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get Bike by ID", description = "Retrieve a bike using its unique ID")
  public ResponseEntity<BikeResponseDTO> getBikeById(@PathVariable("id") Long id) {
    BikeResponseDTO response = bikeService.findByBikeById(id);
    if (response == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(response);
  }

  @PostMapping
  @Operation(summary = "Persist new bike", description = "Add new bike")
  @CacheEvict(value = "bike-cache", allEntries = true)
  public ResponseEntity<BikeResponseDTO> createBike(@RequestBody BikeRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(bikeService.createBike(request));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete bike", description = "Delete a bike")
  @CacheEvict(value = "bike-cache", allEntries = true)
  public ResponseEntity<Void> deleteBikeById(@PathVariable("id") Long id) {
    try {
      bikeService.deleteBikeById(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

  }

  @PutMapping("/{id}")
  @Operation(summary = "Update bike", description = "Update a bike by id")
  @CacheEvict(value = "bike-cache", allEntries = true)
  public ResponseEntity<BikeResponseDTO> updateBike(@PathVariable("id") Long id,
      @RequestBody BikeRequestDTO request) {
    try {
      BikeResponseDTO bike = bikeService.updateBikeById(id, request);
      return ResponseEntity.status(HttpStatus.OK).body(bike);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}
