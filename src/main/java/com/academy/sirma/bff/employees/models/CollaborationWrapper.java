package com.academy.sirma.bff.employees.models;

public class CollaborationWrapper {

    private EmployeePair employeePair;

    private CollaborativeWork collaborativeWork;

    public CollaborationWrapper(EmployeePair employeePair, CollaborativeWork collaborativeWork) {
        this.employeePair = employeePair;
        this.collaborativeWork = collaborativeWork;
    }

    public EmployeePair getEmployeePair() {
        return employeePair;
    }

    public void setEmployeePair(EmployeePair employeePair) {
        this.employeePair = employeePair;
    }

    public CollaborativeWork getCollaborativeWork() {
        return collaborativeWork;
    }

    public void setCollaborativeWork(CollaborativeWork collaborativeWork) {
        this.collaborativeWork = collaborativeWork;
    }
}
