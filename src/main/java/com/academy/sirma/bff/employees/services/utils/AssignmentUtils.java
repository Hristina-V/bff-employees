package com.academy.sirma.bff.employees.services.utils;

import com.academy.sirma.bff.employees.models.Assignment;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentUtils {

    public List<Assignment> sortListOfAssignments(List<Assignment> assignments) {
        return assignments
            .stream()
            .sorted((a1, a2) -> {
                Long projectIdA1 = a1.getProjectId();
                Long projectIdA2 = a2.getProjectId();
                int sComp = projectIdA1.compareTo(projectIdA2);

                if (sComp != 0) {
                    // It means that we have different projects
                    return sComp;
                }

                LocalDate startDateA1 = a1.getStartDate();
                LocalDate startDateA2 = a2.getStartDate();
                return startDateA1.compareTo(startDateA2);
            })
            .collect(Collectors.toList());
    }

}
