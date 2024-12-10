package br.com.acbueno.campinas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/v1/rentals")
public class RentController {

  @Autowired
  private RentalService rentalService;


  @GetMapping
  public ResponseEntity<List<RentalResponseDTO>> getAllRent() {
    return ResponseEntity.ok(rentalService.getAllRent());
  }

  @GetMapping("/{idRent}")
  public ResponseEntity<RentalResponseDTO> getRentById(@PathVariable("idRent") Long idRent) {
    return ResponseEntity.ok(rentalService.getRentalById(idRent));
  }

  @PostMapping("/rent")
  public ResponseEntity<RentalResponseDTO> rentBike(@RequestBody RentalRequestDTO request) {
    return ResponseEntity.ok(rentalService.rentBike(request));
  }

  @PostMapping("/return/{rentalId}")
  public ResponseEntity<RentalResponseDTO> returnBike(@PathVariable Long rentalId,
      @RequestParam Long stationId) {
    return ResponseEntity.ok(rentalService.returnBike(rentalId, stationId));
  }

}
