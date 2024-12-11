package br.com.acbueno.campinas.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.acbueno.campinas.dto.BikeRequestDTO;
import br.com.acbueno.campinas.dto.BikeResponseDTO;
import br.com.acbueno.campinas.model.Bike;
import br.com.acbueno.campinas.model.BikeStatus;
import br.com.acbueno.campinas.model.Station;
import br.com.acbueno.campinas.repository.BikeRepository;
import br.com.acbueno.campinas.repository.StationRepository;
import jakarta.transaction.Transactional;

@Service
public class BikeService {

  @Autowired
  private BikeRepository bikeRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private StationRepository stationRepository;

  public Page<BikeResponseDTO> findAll(Pageable pageable) {
    return bikeRepository.findAll(pageable)
        .map(bike -> modelMapper.map(bike, BikeResponseDTO.class));

  }

  public BikeResponseDTO findByBikeById(Long id) {
    return modelMapper.map(bikeRepository.findById(id), BikeResponseDTO.class);
  }

  @Transactional
  public BikeResponseDTO createBike(BikeRequestDTO request) {
    Station station = stationRepository.findByName(request.getStationName());
    Bike bike = modelMapper.map(request, Bike.class);
    bike.setStation(station);
    bikeRepository.save(bike);
    return modelMapper.map(bike, BikeResponseDTO.class);
  }

  @Transactional
  public void deleteBikeById(Long id) {
    bikeRepository.deleteById(id);
  }

  @Transactional
  public BikeResponseDTO updateBikeById(Long id, BikeRequestDTO request) {
    //@formatter:off
    Bike bike = bikeRepository.findById(id).orElseThrow(
        () -> new IllegalStateException(String.format("Not found bike by id %s", id)));
    
    Station station = stationRepository.findByName(request.getStationName());
    bike.setModel(request.getModel());
    bike.setPricePerMinute(request.getPricePerMinute());
    bike.setStation(station);
    bike.setStatus(BikeStatus.AVAILABLE);
    
    Bike bikeSaved = bikeRepository.save(bike);
    
    return modelMapper.map(bikeSaved, BikeResponseDTO.class);   
  }

}
