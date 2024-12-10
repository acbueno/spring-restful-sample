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
import br.com.acbueno.campinas.dto.StationRequestDTO;
import br.com.acbueno.campinas.dto.StationResponseDTO;
import br.com.acbueno.campinas.service.StationService;


@RestController
@RequestMapping("/api/v1/stations")
public class StationController {

  @Autowired
  private StationService stationService;

  @GetMapping
  public ResponseEntity<List<StationResponseDTO>> getAllStations() {
    return ResponseEntity.ok(stationService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<StationResponseDTO> findStationById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(stationService.findStationById(id));
  }

  @PostMapping
  public ResponseEntity<StationResponseDTO> createStation(@RequestBody StationRequestDTO request) {
    return ResponseEntity.ok(stationService.createStation(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStationById(@PathVariable("id") Long id) {
    stationService.deleteStation(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<StationResponseDTO> updateStation(@PathVariable("id") Long id,
      @RequestBody StationRequestDTO request) {
    return ResponseEntity.ok(stationService.updateStationById(id, request));
  }

}
