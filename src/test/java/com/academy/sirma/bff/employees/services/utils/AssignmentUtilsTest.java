package com.academy.sirma.bff.employees.services.utils;

import com.academy.sirma.bff.employees.models.Assignment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class AssignmentUtilsTest {

    private AssignmentUtils assignmentUtils = new AssignmentUtils();

    @Test
    public void it_should_return_sorted_list() {
        Assignment a1 = new Assignment(5,1, LocalDate.of(2023,01,02), LocalDate.of(2023,03,9));
        Assignment a2 = new Assignment(1,1, LocalDate.of(2023,01,13), LocalDate.of(2023,02,22));
        Assignment a3 = new Assignment(1,3, LocalDate.of(2023,01,29), LocalDate.of(2023,07,16));
        Assignment a4 = new Assignment(2,5, LocalDate.of(2023,02,02), LocalDate.of(2023,05,23));
        Assignment a5 = new Assignment(2,5, LocalDate.of(2023,02,22), LocalDate.of(2023,04,12));

        List<Assignment> unsortedAssignments = new ArrayList<>();
        unsortedAssignments.add(a2);
        unsortedAssignments.add(a5);
        unsortedAssignments.add(a3);
        unsortedAssignments.add(a1);
        unsortedAssignments.add(a4);

        List<Assignment> expected = new ArrayList<>();
        expected.add(a1);
        expected.add(a2);
        expected.add(a3);
        expected.add(a4);
        expected.add(a5);

        List<Assignment> result = assignmentUtils.sortListOfAssignments(unsortedAssignments);

        assertArrayEquals(expected.toArray(), result.toArray());
    }
}
