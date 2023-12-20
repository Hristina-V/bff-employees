package com.academy.sirma.bff.employees.models;

import java.util.List;

public class CollaborativeWork {

    private long totalCollaborationDays;

    private List<CollaborationPerAssignment> collaborationPerAssignments;

    public CollaborativeWork(long totalCollaborationDays, List<CollaborationPerAssignment> collaborationPerAssignments) {
        this.totalCollaborationDays = totalCollaborationDays;
        this.collaborationPerAssignments = collaborationPerAssignments;
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
