package com.academy.sirma.bff.employees.models;

import java.util.Objects;

public class EmployeePair {

    private long smallerEmployeeId;

    private long higherEmployeeId;

    public EmployeePair(long smallerEmployeeId, long higherEmployeeId) {
        this.smallerEmployeeId = smallerEmployeeId;
        this.higherEmployeeId = higherEmployeeId;
    }

    public long getSmallerEmployeeId() {
        return smallerEmployeeId;
    }

    public long getHigherEmployeeId() {
        return higherEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }


        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeePair that = (EmployeePair) o;

        return smallerEmployeeId == that.smallerEmployeeId && higherEmployeeId == that.higherEmployeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(smallerEmployeeId, higherEmployeeId);
    }
}
