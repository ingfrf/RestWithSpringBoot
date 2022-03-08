package com.enmivida.rest.repository;

import com.enmivida.rest.data.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Page<Person> findPersonByFirstNameContaining(@Param("firstName") String firstName, Pageable pageable);
}
