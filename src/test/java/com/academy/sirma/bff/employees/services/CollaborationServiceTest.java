package com.academy.sirma.bff.employees.services;

import com.academy.sirma.bff.employees.models.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

public class CollaborationServiceTest {

    @Test
    public void should_return_actual_collaborations() {
        Assignment a1 = new Assignment(5,1, LocalDate.of(2023,01,02), LocalDate.of(2023,03,9));
        Assignment a2 = new Assignment(1,1, LocalDate.of(2023,01,13), LocalDate.of(2023,02,22));
        Assignment a3 = new Assignment(1,3, LocalDate.of(2023,01,29), LocalDate.of(2023,07,16));
        Assignment a4 = new Assignment(2,5, LocalDate.of(2023,02,02), LocalDate.of(2023,05,23));
        Assignment a5 = new Assignment(2,5, LocalDate.of(2023,02,22), LocalDate.of(2023,04,12));

        List<Assignment> assignmentList = new ArrayList<>();
        assignmentList.add(a1);
        assignmentList.add(a2);
        assignmentList.add(a3);
        assignmentList.add(a4);
        assignmentList.add(a5);

        Map<EmployeePair, CollaborativeWork> expectedCollaborationsPerPair = new HashMap<>();
        expectedCollaborationsPerPair.put(
            new EmployeePair(1,5),
            new CollaborativeWork(Arrays.asList(
                new CollaborationPerAssignment(1L, new CollaborationTimeFrame(LocalDate.of(2023,01,13), LocalDate.of(2023,02,22)))
            ))
        );
    }

}
