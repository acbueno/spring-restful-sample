package br.com.acbueno.campinas.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.campinas.dto.RentalRequestDTO;
import br.com.acbueno.campinas.dto.RentalResponseDTO;
import br.com.acbueno.campinas.model.Bike;
import br.com.acbueno.campinas.model.BikeStatus;
import br.com.acbueno.campinas.model.Rental;
import br.com.acbueno.campinas.model.RentalStatus;
import br.com.acbueno.campinas.model.Station;
import br.com.acbueno.campinas.model.User;
import br.com.acbueno.campinas.repository.BikeRepository;
import br.com.acbueno.campinas.repository.RentalRepository;
import br.com.acbueno.campinas.repository.StationRepository;
import br.com.acbueno.campinas.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  @Autowired
  private BikeRepository bikeRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private StationRepository stationRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Transactional
  public RentalResponseDTO rentBike(RentalRequestDTO request) {

    //@formatter:off
    Bike bike = bikeRepository.findById(request.getBikeId())
        .orElseThrow(() -> new IllegalArgumentException("Bike not found"));

    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    if (bike.getStatus() != BikeStatus.AVAILABLE) {
      throw new IllegalStateException("Bike is not available");
    }
    
    //@formatter:on

    bike.setStatus(BikeStatus.RENTED);
    bikeRepository.save(bike);

    Rental rental = new Rental();
    rental.setBike(bike);
    rental.setUser(user);
    rental.setRentalDate(LocalDateTime.now());
    rental.setReturnDate(null);
    rental.setStation(bike.getStation());
    rentalRepository.save(rental);

    return modelMapper.map(rental, RentalResponseDTO.class);
  }

  public RentalResponseDTO returnBike(Long rentalId, Long stationId) {
    Rental rental = rentalRepository.findById(rentalId)
        .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

    if (rental.getReturnDate() != null) {
      throw new IllegalStateException("This rental has already been completed");
    }

    Bike bike = rental.getBike();
    Station station = stationRepository.findById(stationId)
        .orElseThrow(() -> new IllegalStateException("Station not found"));
    bike.setStatus(BikeStatus.AVAILABLE);
    bike.setStation(station);
    bikeRepository.save(bike);
    rental.setReturnDate(LocalDateTime.now());
    long minutes = Math.max(1, Duration.between(rental.getRentalDate(), rental.getReturnDate()).toMinutes());
    int timeRent = (int) Math.ceil(minutes / 60.0);
    rental.setRentalCost(timeRent * bike.getPricePerMinute());
    rental.setStatus(RentalStatus.COMPLETED);
    rentalRepository.save(rental);

    return modelMapper.map(rental, RentalResponseDTO.class);

  }

  public List<RentalResponseDTO> getAllRent() {
    //@formatter:off
    return rentalRepository.findAll().stream()
        .map(rent -> modelMapper.map(rent, RentalResponseDTO.class))
        .toList();
    //@formatter:on
  }

  public RentalResponseDTO getRentalById(Long rentalId) {
    return modelMapper.map(rentalRepository.findById(rentalId).get(), RentalResponseDTO.class);
  }

}
