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
import br.com.acbueno.campinas.dto.BikeRequestDTO;
import br.com.acbueno.campinas.dto.BikeResponseDTO;
import br.com.acbueno.campinas.service.BikeService;

@RestController
@RequestMapping("/api/v1/bikes")
public class BikeController {

  @Autowired
  private BikeService bikeService;

  @GetMapping
  public ResponseEntity<List<BikeResponseDTO>> getAllBikes() {
    List<BikeResponseDTO> bikes = bikeService.findAll();
    return ResponseEntity.ok(bikes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BikeResponseDTO> getBikeById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(bikeService.findByBikeById(id));
  }

  @PostMapping
  public ResponseEntity<BikeResponseDTO> createBike(@RequestBody BikeRequestDTO request) {
    return ResponseEntity.ok(bikeService.createBike(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBikeById(@PathVariable("id") Long id) {
    bikeService.deleteBikeById(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<BikeResponseDTO> updateBike(@PathVariable("id") Long id,
      @RequestBody BikeRequestDTO request) {
    return ResponseEntity.ok(bikeService.updateBikeById(id, request));
  }

}
