package com.academy.sirma.bff.employees.models;

public class CollaborationPerAssignment {
    private Long projectId;

    private CollaborationTimeFrame collaborationTimeFrame;

    public CollaborationPerAssignment(Long projectId, CollaborationTimeFrame collaborationTimeFrame) {
        this.projectId = projectId;
        this.collaborationTimeFrame = collaborationTimeFrame;
    }

    public Long getProjectId() {
        return projectId;
    }

    public CollaborationTimeFrame getCollaborationTimeFrame() {
        return collaborationTimeFrame;
    }
}
