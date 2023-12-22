package com.academy.sirma.bff.employees.mappers;

import com.academy.sirma.bff.employees.entities.AssignmentEntity;
import com.academy.sirma.bff.employees.models.Assignment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

    public List<AssignmentEntity> toListOfEntities(List<Assignment> assignments) {
        return assignments.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public AssignmentEntity toEntity(Assignment assignment) {
        return new AssignmentEntity(
            assignment.getEmployeeId(), assignment.getProjectId(), assignment.getStartDate(), assignment.getEndDate()
        );
    }

    public List<Assignment> toListOfAssignments(List<AssignmentEntity> assignmentEntities) {
        return assignmentEntities.stream().map(this::toAssignment).collect(Collectors.toList());
    }

    public Assignment toAssignment(AssignmentEntity assignmentEntity) {
        return new Assignment(
            assignmentEntity.getEmployeeId(), assignmentEntity.getProjectId(), assignmentEntity.getStartDate(), assignmentEntity.getEndDate()
        );
    }

}
