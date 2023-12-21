package com.academy.sirma.bff.employees.repositories;

import com.academy.sirma.bff.employees.entities.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {


}
