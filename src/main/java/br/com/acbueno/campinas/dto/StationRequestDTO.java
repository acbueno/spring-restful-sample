package br.com.acbueno.campinas.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StationRequestDTO {

  @NotBlank(message = "Station name is required")
  private String name;

}
