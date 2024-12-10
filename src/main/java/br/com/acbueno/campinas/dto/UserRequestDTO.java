package br.com.acbueno.campinas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

  @JsonProperty("name")
  @NotBlank(message = "Name is rerquited")
  private String name;

  @JsonProperty("email")
  @Email(message = "Email should be valid")
  private String email;


}
