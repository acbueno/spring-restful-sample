package br.com.acbueno.campinas.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.campinas.dto.StationRequestDTO;
import br.com.acbueno.campinas.dto.StationResponseDTO;
import br.com.acbueno.campinas.model.Station;
import br.com.acbueno.campinas.repository.StationRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
public class StationService {

  @Autowired
  private StationRepository stationRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<StationResponseDTO> findAll() {
    //@formatter:off
    return stationRepository.findAll().stream()
         .map(station -> modelMapper.map(station, StationResponseDTO.class))
     .toList();
    //@formatter:on
  }

  public StationResponseDTO createStation(@RequestBody StationRequestDTO request) {
    try {
      Station station = stationRepository.save(modelMapper.map(request, Station.class));
      return modelMapper.map(station, StationResponseDTO.class);
    } catch (Exception e) {
      throw new RuntimeException("Erro persist statiom");
    }
  }

  public StationResponseDTO findStationById(Long id) {
    return modelMapper.map(stationRepository.findById(id), StationResponseDTO.class);
  }

  public void deleteStation(Long idStation) {
    stationRepository.deleteById(idStation);
  }

  public StationResponseDTO updateStationById(Long id, StationRequestDTO request) {
    Station station = stationRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("Not found Station by id"));
    station.setName(request.getName());
    Station stationSaved = stationRepository.save(station);

    return modelMapper.map(stationSaved, StationResponseDTO.class);
  }

}
