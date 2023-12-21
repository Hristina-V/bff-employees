package com.academy.sirma.bff.employees.mappers;

import com.academy.sirma.bff.employees.entities.CollaborationEntity;
import com.academy.sirma.bff.employees.entities.CollaborativeWorkEntity;
import com.academy.sirma.bff.employees.models.CollaborationPerAssignment;
import com.academy.sirma.bff.employees.models.CollaborationTimeFrame;
import com.academy.sirma.bff.employees.models.CollaborativeWork;
import com.academy.sirma.bff.employees.models.EmployeePair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CollaborationMapper {

    public CollaborationEntity toCollaborationEntity(Map.Entry<EmployeePair, CollaborativeWork> entry) {
        EmployeePair employeePair = entry.getKey();
        CollaborationEntity collaborationEntity = new CollaborationEntity(employeePair.getSmallerEmployeeId(), employeePair.getHigherEmployeeId());

        List<CollaborativeWorkEntity> collaborativeWorkEntities = toListOfCollaborativeWorkEntity(entry.getValue().getCollaborationPerAssignments(), collaborationEntity);

        collaborationEntity.setCollaborativeWorkEntity(collaborativeWorkEntities);

        return collaborationEntity;
    }

    public List<CollaborationEntity> toListOfCollaborationEntity(Map<EmployeePair, CollaborativeWork> collaborations) {
        List<CollaborationEntity> entities = new ArrayList<>();

        for (Map.Entry<EmployeePair, CollaborativeWork> entry : collaborations.entrySet()) {
            entities.add(toCollaborationEntity(entry));
        }

        return entities;
    }

    public List<CollaborativeWorkEntity> toListOfCollaborativeWorkEntity(List<CollaborationPerAssignment> collaborationPerAssignments, CollaborationEntity collaborationEntity) {
        return collaborationPerAssignments
                .stream()
                .map(x -> toCollaborativeWorkEntity(x, collaborationEntity))
                .collect(Collectors.toList());
    }

    public CollaborativeWorkEntity toCollaborativeWorkEntity(CollaborationPerAssignment collaborationPerAssignment, CollaborationEntity collaborationEntity) {
        CollaborationTimeFrame collaborationTimeFrame = collaborationPerAssignment.getCollaborationTimeFrame();

        return new CollaborativeWorkEntity(collaborationPerAssignment.getProjectId(), collaborationTimeFrame.getStartDate(), collaborationTimeFrame.getEndDate(), collaborationEntity);
    }

    public Map<EmployeePair, CollaborativeWork> toCollaborationsMap(List<CollaborationEntity> collaborationEntities) {
        Map<EmployeePair, CollaborativeWork> map = new HashMap<>();

        for (CollaborationEntity entity : collaborationEntities) {
            EmployeePair key = new EmployeePair(entity.getSmallerEmployeeId(), entity.getHigherEmployeeId());
            CollaborativeWork value = new CollaborativeWork(toListOfCollaborationPerAssignment(entity.getCollaborativeWorkEntity()));
            map.put(key, value);
        }

        return map;
    }

    public List<CollaborationPerAssignment> toListOfCollaborationPerAssignment(List<CollaborativeWorkEntity> collaborativeWork) {
        return collaborativeWork.stream().map(this::toCollaborationPerAssignment).collect(Collectors.toList());
    }

    public CollaborationPerAssignment toCollaborationPerAssignment(CollaborativeWorkEntity collaborativeWorkEntity) {
        CollaborationTimeFrame collaborationTimeFrame = new CollaborationTimeFrame(collaborativeWorkEntity.getStartDate(), collaborativeWorkEntity.getEndDate());
        return new CollaborationPerAssignment(collaborativeWorkEntity.getProjectId(),collaborationTimeFrame);
    }
}
