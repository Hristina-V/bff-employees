package com.academy.sirma.bff.employees;

import com.academy.sirma.bff.employees.models.CollaborationPerAssignment;
import com.academy.sirma.bff.employees.models.CollaborationTimeFrame;
import com.academy.sirma.bff.employees.models.CollaborativeWork;
import com.academy.sirma.bff.employees.models.EmployeePair;
import com.academy.sirma.bff.employees.services.CollaborationService;
import com.academy.sirma.bff.employees.services.csv.аssignments.AssignmentsCsvFileReader;
import com.academy.sirma.bff.employees.services.helpers.CollaborationHelper;
import com.academy.sirma.bff.employees.services.utils.AssignmentUtils;
import com.academy.sirma.bff.employees.services.utils.LongUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class BffEmployeesCli {

    public static void main(String args[]) {
        // Init dependencies
        CollaborationService collaborationService = initCollaborationService();

        // Read data from input file
        Map<EmployeePair, CollaborativeWork> collaborations = collaborationService.findCollaborations();

        // Find pair with most days of collaboration
        EmployeePair targetPair = collaborationService.findEmployeesWithMostCollaborationDays(collaborations);
        CollaborativeWork collaborativeWork = collaborations.get(targetPair);

        printResult(targetPair, collaborativeWork);
    }

    private static void printResult(EmployeePair targetPair, CollaborativeWork collaborativeWork) {
        System.out.println(
                targetPair.getSmallerEmployeeId() + ", "
                        + targetPair.getHigherEmployeeId() + ", "
                        + collaborativeWork.getTotalCollaborationDays()
        );

        for (CollaborationPerAssignment collaboration : collaborativeWork.getCollaborationPerAssignments()) {
            CollaborationTimeFrame timeFrame = collaboration.getCollaborationTimeFrame();
            long totalDaysPerProject = DAYS.between(timeFrame.getStartDate(), timeFrame.getEndDate());

            System.out.println(collaboration.getProjectId() + ", " + totalDaysPerProject);
        }
    }

    @Bean
    private static CollaborationService initCollaborationService() {
        return new CollaborationService(initAssignmentUtilsService(), initAssignmentsCsvFileReader(), new CollaborationHelper(), new LongUtils());
    }

    @Bean
    private static AssignmentsCsvFileReader initAssignmentsCsvFileReader() {
        return new AssignmentsCsvFileReader();
    };

    @Bean
    private static AssignmentUtils initAssignmentUtilsService() {
        return new AssignmentUtils();
    }

}
