package br.com.acbueno.campinas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.acbueno.campinas.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

  public Station findByName(String name);

}
