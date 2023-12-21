package com.academy.sirma.bff.employees.models;

import java.util.List;

public class CollaborativeWork {

    private List<CollaborationPerAssignment> collaborationPerAssignments;

    private long totalCollaborationDays;

    public CollaborativeWork(List<CollaborationPerAssignment> collaborationPerAssignments) {
        this.collaborationPerAssignments = collaborationPerAssignments;
        this.totalCollaborationDays = collaborationPerAssignments.stream().mapToLong(x -> x.getCollaborationTimeFrame().getDays()).sum();
    }

    public long getTotalCollaborationDays() {
        return totalCollaborationDays;
    }

    public List<CollaborationPerAssignment> getCollaborationPerAssignments() {
        return collaborationPerAssignments;
    }

    public void incrementTotalCollaborationDays(long increment) {
        this.totalCollaborationDays += increment;
    }
}
