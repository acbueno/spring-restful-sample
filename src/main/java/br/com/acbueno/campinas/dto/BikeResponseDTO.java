package br.com.acbueno.campinas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BikeResponseDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("model")
  private String model;

  @JsonProperty("status")
  private String status;

  @JsonProperty("station_name")
  private String stationName;

  @JsonProperty("price_per_minute")
  private Double pricePerMinute;

}
