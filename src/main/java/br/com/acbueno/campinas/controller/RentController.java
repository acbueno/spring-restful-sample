package br.com.acbueno.campinas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.acbueno.campinas.dto.RentalRequestDTO;
import br.com.acbueno.campinas.dto.RentalResponseDTO;
import br.com.acbueno.campinas.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/rentals")
@Tag(name = "Rentals", description = "Endpoints for managing rentals")
public class RentController {

  @Autowired
  private RentalService rentalService;


  @GetMapping
  @Operation(summary = "Get all rentals", description = "List all rentals")
  @Cacheable("rent-cache")
  public ResponseEntity<Page<RentalResponseDTO>> getAllRent(Pageable pageable) {
    return ResponseEntity.ok(rentalService.getAllRent(pageable));
  }

  @GetMapping("/{idRent}")
  @Operation(summary = "Get rent ", description = "Get rent by id")
  public ResponseEntity<RentalResponseDTO> getRentById(@PathVariable("idRent") Long idRent) {
    RentalResponseDTO response = rentalService.getRentalById(idRent);
    if (response == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(rentalService.getRentalById(idRent));
  }

  @PostMapping("/rent")
  @Operation(summary = "Persist new rent", description = "Add new rent bike")
  @CacheEvict(value = "rent-cache", allEntries = true)
  public ResponseEntity<RentalResponseDTO> rentBike(@RequestBody RentalRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(rentalService.rentBike(request));
  }

  @PostMapping("/return/{rentalId}")
  @Operation(summary = "Persist renturn bike", description = "Add event return bike")
  @CacheEvict(value = "rent-cache", allEntries = true)
  public ResponseEntity<RentalResponseDTO> returnBike(@PathVariable Long rentalId,
      @RequestParam Long stationId) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(rentalService.returnBike(rentalId, stationId));
  }

}
