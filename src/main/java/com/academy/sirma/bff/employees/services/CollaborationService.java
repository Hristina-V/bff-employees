package com.academy.sirma.bff.employees.services;

import com.academy.sirma.bff.employees.models.*;
import com.academy.sirma.bff.employees.services.csv.аssignments.AssignmentsCsvFileReader;
import com.academy.sirma.bff.employees.services.helpers.CollaborationHelper;
import com.academy.sirma.bff.employees.services.utils.AssignmentUtils;
import com.academy.sirma.bff.employees.services.utils.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<CollaborationWrapper> findEmployeesWithMostCollaborationDays(List<CollaborationWrapper> collaborations, int count) {
        validateCount(count);

        List<CollaborationWrapper> sorted = collaborations
            .stream()
            .sorted((e1, e2) ->
                Long.compare(e2.getCollaborativeWork().getTotalCollaborationDays(), e1.getCollaborativeWork().getTotalCollaborationDays())
            )
            .collect(Collectors.toList());

        if(count >= collaborations.size()) {
            return sorted;
        }

        return sorted.subList(0, count);
    }

    private void validateCount(int count) {
        if(count < 1) {
            //TODO handle in ExceptionHandler & map to HTTP status code 400 - Bad Request
            throw new IllegalStateException("You need to provide a positive count");
        }
    }
}
