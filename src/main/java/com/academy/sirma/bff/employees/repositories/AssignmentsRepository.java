package com.academy.sirma.bff.employees.repositories;

import com.academy.sirma.bff.employees.entities.AssignmentEntity;
import com.academy.sirma.bff.employees.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentsRepository extends JpaRepository<AssignmentEntity, Long> {


}
