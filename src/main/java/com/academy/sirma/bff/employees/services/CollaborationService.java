package com.academy.sirma.bff.employees.services;

import com.academy.sirma.bff.employees.entities.CollaborationEntity;
import com.academy.sirma.bff.employees.mappers.CollaborationMapper;
import com.academy.sirma.bff.employees.models.Assignment;
import com.academy.sirma.bff.employees.models.CollaborationPerAssignment;
import com.academy.sirma.bff.employees.models.CollaborationTimeFrame;
import com.academy.sirma.bff.employees.models.CollaborativeWork;
import com.academy.sirma.bff.employees.models.EmployeePair;
import com.academy.sirma.bff.employees.services.csv.аssignments.AssignmentsCsvFileReader;
import com.academy.sirma.bff.employees.services.helpers.CollaborationHelper;
import com.academy.sirma.bff.employees.services.utils.AssignmentUtils;
import com.academy.sirma.bff.employees.services.utils.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollaborationService {

    private final AssignmentUtils assignmentUtils;

    private final AssignmentsCsvFileReader assignmentsCsvFileReader;

    private final CollaborationHelper collaborationHelper;

    private final LongUtils longUtils;

    @Autowired
    public CollaborationService(AssignmentUtils assignmentUtils,
                                AssignmentsCsvFileReader assignmentsCsvFileReader,
                                CollaborationHelper collaborationHelper,
                                LongUtils longUtils) {
        this.assignmentUtils = assignmentUtils;
        this.assignmentsCsvFileReader = assignmentsCsvFileReader;
        this.collaborationHelper = collaborationHelper;
        this.longUtils = longUtils;
    }

    public Map<EmployeePair, CollaborativeWork> findCollaborations() {
        List<Assignment> assignments = assignmentsCsvFileReader.readFromFile();

        Map<EmployeePair, CollaborativeWork> collaborations = findCollaborations(assignments);

        return collaborations;
    }

    public Map<EmployeePair, CollaborativeWork> findCollaborations(List<Assignment> assignments) {
        List<Assignment> sortedAssignments = assignmentUtils.sortListOfAssignments(assignments);

        Map<EmployeePair, CollaborativeWork> collaborationsPerPair = new HashMap<>();

        for (int i = 0; i < sortedAssignments.size(); i++) {
            Assignment firstEmployeeAssignment = assignments.get(i);

            for (int j = i + 1; j < assignments.size(); j++) {
                Assignment secondEmployeeAssignment = assignments.get(j);

                boolean isCollaboration = collaborationHelper.isCollaboration(firstEmployeeAssignment, secondEmployeeAssignment);

                if(isCollaboration) {
                    EmployeePair employeePair = new EmployeePair(
                        longUtils.getSmaller(firstEmployeeAssignment.getEmployeeId(), secondEmployeeAssignment.getEmployeeId()),
                        longUtils.getBigger(firstEmployeeAssignment.getEmployeeId(), secondEmployeeAssignment.getEmployeeId())
                    );

                    if(collaborationsPerPair.containsKey(employeePair)) {
                        long projectId = firstEmployeeAssignment.getProjectId();
                        CollaborationTimeFrame collaborationTimeFrame = CollaborationHelper.calculateCollaborationTimeFrame(firstEmployeeAssignment, secondEmployeeAssignment);
                        CollaborationPerAssignment collaborationPerAssignment = new CollaborationPerAssignment(projectId, collaborationTimeFrame);

                        CollaborativeWork collaborativeWork = collaborationsPerPair.get(employeePair);
                        collaborativeWork.getCollaborationPerAssignments().add(collaborationPerAssignment);
                        collaborativeWork.incrementTotalCollaborationDays(collaborationTimeFrame.getDays());
                    } else {
                        CollaborationTimeFrame collaborationTimeFrame = CollaborationHelper.calculateCollaborationTimeFrame(firstEmployeeAssignment, secondEmployeeAssignment);
                        CollaborationPerAssignment collaborationPerAssignment = new CollaborationPerAssignment(firstEmployeeAssignment.getProjectId(), collaborationTimeFrame);
                        CollaborativeWork collaborativeWork = new CollaborativeWork(Arrays.asList(collaborationPerAssignment));

                        collaborationsPerPair.put(employeePair, collaborativeWork);
                    }
                }

            }
        }
        return collaborationsPerPair;
    }

    public EmployeePair findEmployeesWithMostCollaborationDays(Map<EmployeePair, CollaborativeWork> collaborations) {
        EmployeePair targetPair = null;
        CollaborativeWork collaborativeWork = null;

        for (Map.Entry<EmployeePair, CollaborativeWork> entry : collaborations.entrySet()) {
            long collaborationDays = entry.getValue().getTotalCollaborationDays();

            if (collaborativeWork == null || collaborationDays > collaborativeWork.getTotalCollaborationDays()) {
                collaborativeWork = entry.getValue();
                targetPair = entry.getKey();
            }
        }

        return targetPair;
    }

    public List<CollaborativeWork> findEmployeesWithMostCollaborationDays(Map<EmployeePair, CollaborativeWork> collaborations, int count) {
        if(count < 1) {
            //TODO handle in ExceptionHandler & map to HTTP status code 400 - Bad Request
            throw new IllegalStateException("You need to provide a positive count");
        }

        if(collaborations.size() <= count) {
            return collaborations
                    .entrySet()
                    .stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }

        if(count == 1) {
            //TODO Consider if we need a dedicate method for top 1 or we can reuse the new generic logic from this method.
            EmployeePair targetPair = findEmployeesWithMostCollaborationDays(collaborations);
            CollaborativeWork collaborativeWork = collaborations.get(targetPair);
            return Arrays.asList(collaborativeWork);
        }

        //TODO implement logic
        throw new RuntimeException();
    }
}
