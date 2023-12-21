package com.academy.sirma.bff.employees.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "collaborative_work")
public class CollaborativeWorkEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "project_id")
    private long projectId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaboration_id")  // Make sure to use the correct column name
    private CollaborationEntity collaborationEntity;

    public CollaborativeWorkEntity() { }

    public CollaborativeWorkEntity(long projectId, LocalDate startDate, LocalDate endDate) {
        this(projectId, startDate, endDate, null);
    }

    public CollaborativeWorkEntity(long projectId, LocalDate startDate, LocalDate endDate, CollaborationEntity collaborationEntity) {
        this.projectId = projectId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.collaborationEntity = collaborationEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public CollaborationEntity getCollaborationEntity() {
        return collaborationEntity;
    }

    public void setCollaborationEntity(CollaborationEntity collaborationEntity) {
        this.collaborationEntity = collaborationEntity;
    }
}
