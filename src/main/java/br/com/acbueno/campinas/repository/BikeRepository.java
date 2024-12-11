package br.com.acbueno.campinas.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.acbueno.campinas.model.Bike;
import br.com.acbueno.campinas.model.BikeStatus;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

  List<Bike> findByStatus(BikeStatus status);

  Page<Bike> findAll(Pageable pageable);

}
