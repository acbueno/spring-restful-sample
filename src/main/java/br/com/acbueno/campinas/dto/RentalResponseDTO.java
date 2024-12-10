package br.com.acbueno.campinas.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RentalResponseDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("bike_model")
  private String bikeModel;

  @JsonProperty("user_name")
  private String userName;

  @JsonProperty("rental_date")
  private LocalDateTime rentalDate;

  @JsonProperty("return_date")
  private LocalDateTime returnDate;

  @JsonProperty("rental_cost")
  private Double rentalCost;

  @JsonProperty("status")
  private String status;

}
