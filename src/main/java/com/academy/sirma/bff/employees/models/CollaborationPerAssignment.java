package com.academy.sirma.bff.employees.models;

import java.time.LocalDate;

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
