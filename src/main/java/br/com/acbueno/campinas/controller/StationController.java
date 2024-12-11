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
import br.com.acbueno.campinas.dto.StationRequestDTO;
import br.com.acbueno.campinas.dto.StationResponseDTO;
import br.com.acbueno.campinas.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/v1/stations")
@Tag(name = "Station", description = "Endpoints for managing station")
public class StationController {

  @Autowired
  private StationService stationService;

  @GetMapping
  @Operation(summary = "Get all Station", description = "List all station")
  @Cacheable("station-cache")
  public ResponseEntity<Page<StationResponseDTO>> getAllStations(Pageable pageable) {
    return ResponseEntity.ok(stationService.findAll(pageable));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get Station by ID", description = "Retrieve a station using its unique ID")
  public ResponseEntity<StationResponseDTO> findStationById(@PathVariable("id") Long id) {
    StationResponseDTO response = stationService.findStationById(id);
    if (response == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(response);
  }

  @PostMapping
  @Operation(summary = "Persist new station", description = "Add new station")
  @CacheEvict(value = "station-cache", allEntries = true)
  public ResponseEntity<StationResponseDTO> createStation(@RequestBody StationRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(stationService.createStation(request));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete station", description = "Delete a station by id")
  @CacheEvict(value = "station-cache", allEntries = true)
  public ResponseEntity<Void> deleteStationById(@PathVariable("id") Long id) {
    try {
      stationService.deleteStation(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update station", description = "Update a station by id")
  @CacheEvict(value = "station-cache", allEntries = true)
  public ResponseEntity<StationResponseDTO> updateStation(@PathVariable("id") Long id,
      @RequestBody StationRequestDTO request) {
    try {
      StationResponseDTO response = stationService.updateStationById(id, request);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

}
