package com.academy.sirma.bff.employees.repositories;

import com.academy.sirma.bff.employees.entities.CollaborationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaborationsRepository extends JpaRepository<CollaborationEntity, Long> {

}
