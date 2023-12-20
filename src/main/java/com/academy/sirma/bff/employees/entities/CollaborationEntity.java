package com.academy.sirma.bff.employees.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity(name = "collaborations")
public class CollaborationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private long smallerEmployeeId;

    private long higherEmployeeId;

    private long totalCollaborationDays;

    @OneToMany
    private Set<CollaborativeWorkEntity> collaborativeWorkEntity;

    public CollaborationEntity() { }

    public CollaborationEntity(long smallerEmployeeId, long higherEmployeeId, long totalCollaborationDays, Set<CollaborativeWorkEntity> collaborativeWorkEntity) {
        this.smallerEmployeeId = smallerEmployeeId;
        this.higherEmployeeId = higherEmployeeId;
        this.totalCollaborationDays = totalCollaborationDays;
        this.collaborativeWorkEntity = collaborativeWorkEntity;
    }

    public long getId() {
        return id;
    }

    public long getSmallerEmployeeId() {
        return smallerEmployeeId;
    }

    public void setSmallerEmployeeId(long smallerEmployeeId) {
        this.smallerEmployeeId = smallerEmployeeId;
    }

    public long getHigherEmployeeId() {
        return higherEmployeeId;
    }

    public void setHigherEmployeeId(long higherEmployeeId) {
        this.higherEmployeeId = higherEmployeeId;
    }

    public long getTotalCollaborationDays() {
        return totalCollaborationDays;
    }

    public void setTotalCollaborationDays(long totalCollaborationDays) {
        this.totalCollaborationDays = totalCollaborationDays;
    }

    public Set<CollaborativeWorkEntity> getCollaborativeWorkEntity() {
        return collaborativeWorkEntity;
    }

    public void setCollaborativeWorkEntity(Set<CollaborativeWorkEntity> collaborativeWorkEntity) {
        this.collaborativeWorkEntity = collaborativeWorkEntity;
    }
}
