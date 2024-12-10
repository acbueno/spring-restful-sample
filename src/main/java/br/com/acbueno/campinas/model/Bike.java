package br.com.acbueno.campinas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bike")
@Data
public class Bike {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String model;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BikeStatus status = BikeStatus.AVAILABLE;

  @Column(nullable = false, name = "price_per_minute")
  private Double pricePerMinute;

  @ManyToOne
  private Station station;

}
