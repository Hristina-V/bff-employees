package com.academy.sirma.bff.employees.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "collaborations")
public class CollaborationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "smaller_employee_id")
    private long smallerEmployeeId;

    @Column(name = "higher_employee_id")
    private long higherEmployeeId;

    @OneToMany(mappedBy = "collaborationEntity", cascade = { CascadeType.ALL })
    private List<CollaborativeWorkEntity> collaborativeWorkEntity;

    public CollaborationEntity() { }

    public CollaborationEntity(long smallerEmployeeId, long higherEmployeeId) {
        this(smallerEmployeeId, higherEmployeeId, null);
    }

    public CollaborationEntity(long smallerEmployeeId, long higherEmployeeId, List<CollaborativeWorkEntity> collaborativeWorkEntity) {
        this.smallerEmployeeId = smallerEmployeeId;
        this.higherEmployeeId = higherEmployeeId;
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

    public List<CollaborativeWorkEntity> getCollaborativeWorkEntity() {
        return collaborativeWorkEntity;
    }

    public void setCollaborativeWorkEntity(List<CollaborativeWorkEntity> collaborativeWorkEntity) {
        this.collaborativeWorkEntity = collaborativeWorkEntity;
    }
}
