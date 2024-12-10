package br.com.acbueno.campinas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import br.com.acbueno.campinas.model.Rental;

@Service
public interface RentalRepository extends JpaRepository<Rental, Long> {

}
