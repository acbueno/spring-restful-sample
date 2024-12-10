package br.com.acbueno.campinas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RentalRequestDTO {

  @JsonProperty("bike_id")
  private Long bikeId;

  @JsonProperty("user_id")
  private Long userId;

}
