package br.com.acbueno.campinas.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "bike_id", nullable = false)
  private Bike bike;

  @ManyToOne
  @JoinColumn(name = "station_id", nullable = false)
  private Station station;

  @Column(nullable = false, name = "rental_date")
  private LocalDateTime rentalDate;

  @Column(name = "return_date")
  private LocalDateTime returnDate;

  @Column(name = "rental_cost")
  private Double rentalCost;

  @Enumerated(EnumType.STRING)
  @Column(nullable = true)
  private RentalStatus status = RentalStatus.ONGOING;

}
