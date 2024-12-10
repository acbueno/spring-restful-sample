package br.com.acbueno.campinas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.acbueno.campinas.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
