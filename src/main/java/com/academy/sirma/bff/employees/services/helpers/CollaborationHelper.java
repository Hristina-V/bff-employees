package com.academy.sirma.bff.employees.services.helpers;

import com.academy.sirma.bff.employees.models.Assignment;
import com.academy.sirma.bff.employees.models.CollaborationTimeFrame;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CollaborationHelper {
    /**
     * We would like to check if two employees have worked together on the same project.
     * We already know that the list of assignments is sorted by start date => we can have this in mind when calculating time periods
     */
    public boolean isCollaboration(Assignment firstEmployeeAssignment, Assignment secondEmployeeAssignment) {
        if(firstEmployeeAssignment.getEmployeeId() == secondEmployeeAssignment.getEmployeeId()) {
            return false;
        }

        if (firstEmployeeAssignment.getProjectId() != secondEmployeeAssignment.getProjectId()) {
            return false;
        }

        LocalDate feStartDate = firstEmployeeAssignment.getStartDate();
        LocalDate feEndDate = firstEmployeeAssignment.getEndDate() != null ? firstEmployeeAssignment.getEndDate() : LocalDate.now();
        LocalDate seStartDate = secondEmployeeAssignment.getStartDate();
        LocalDate seEndDate = secondEmployeeAssignment.getEndDate() != null ? secondEmployeeAssignment.getEndDate() : LocalDate.now();

        if (seStartDate.isBefore(feStartDate) && seEndDate.isBefore(feStartDate)
            || seStartDate.isAfter(feEndDate) && seEndDate.isAfter(feEndDate)) {
            return false;
        }
        return true;
    }

    public static CollaborationTimeFrame calculateCollaborationTimeFrame(Assignment firstEmployeeAssignment, Assignment secondEmployeeAssignment) {
        LocalDate feStartDate = firstEmployeeAssignment.getStartDate();
        LocalDate feEndDate = firstEmployeeAssignment.getEndDate() != null ? firstEmployeeAssignment.getEndDate() : LocalDate.now();
        LocalDate seStartDate = secondEmployeeAssignment.getStartDate();
        LocalDate seEndDate = secondEmployeeAssignment.getEndDate() != null ? secondEmployeeAssignment.getEndDate() : LocalDate.now();

        LocalDate startDate = feStartDate.isAfter(seStartDate) ? feStartDate : seStartDate;
        LocalDate endDate = feEndDate.isBefore(seEndDate) ? feEndDate : seEndDate;

        return new CollaborationTimeFrame(startDate, endDate);
    }
}
