package com.academy.sirma.bff.employees.services.helpers;

import com.academy.sirma.bff.employees.models.Assignment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CollaborationHelperTest {
    @InjectMocks
    private CollaborationHelper collaborationHelper;

    @Test
    public void it_should_return_false_if_is_the_same_employee() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(3, ChronoUnit.MONTHS),
            LocalDate.now().minus(1, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(1, 3,
            LocalDate.now().minus(3, ChronoUnit.MONTHS),
            LocalDate.now().minus(1, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertFalse(result);
    }

    @Test
    public void it_should_return_false_if_projects_are_different() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(3, ChronoUnit.MONTHS),
            LocalDate.now().minus(1, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 3,
            LocalDate.now().minus(3, ChronoUnit.MONTHS),
            LocalDate.now().minus(1, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertFalse(result);
    }

    @Test
    public void it_should_return_false_if_second_project_start_date_and_end_date_are_before_first_project_start_date() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(2, ChronoUnit.MONTHS),
            LocalDate.now().minus(1, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 2,
            LocalDate.now().minus(4, ChronoUnit.MONTHS),
            LocalDate.now().minus(3, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertFalse(result);
    }

    @Test
    public void it_should_return_false_if_second_project_start_date_and_end_date_are_after_first_project_end_date() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(5, ChronoUnit.MONTHS),
            LocalDate.now().minus(4, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 2,
            LocalDate.now().minus(3, ChronoUnit.MONTHS),
            LocalDate.now().minus(2, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertFalse(result);
    }

    @Test
    public void it_should_return_true_if_second_project_start_date_is_after_first_project_start_date_and_before_first_project_end_date() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(5, ChronoUnit.MONTHS),
            LocalDate.now().minus(2, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 2,
            LocalDate.now().minus(4, ChronoUnit.MONTHS),
            LocalDate.now().minus(2, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertTrue(result);
    }

    @Test
    public void it_should_return_true_if_second_project_end_date_is_after_first_project_start_date_and_before_first_project_end_date() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(4, ChronoUnit.MONTHS),
            LocalDate.now().minus(2, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 2,
            LocalDate.now().minus(5, ChronoUnit.MONTHS),
            LocalDate.now().minus(3, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertTrue(result);
    }

    @Test
    public void it_should_return_true_if_second_project_start_date_is_equal_to_first_project_start_date() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(5, ChronoUnit.MONTHS),
            LocalDate.now().minus(2, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 2,
            LocalDate.now().minus(5, ChronoUnit.MONTHS),
            LocalDate.now().minus(3, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertTrue(result);
    }

    @Test
    public void it_should_return_true_if_second_project_start_date_is_equal_to_first_project_end_date() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(5, ChronoUnit.MONTHS),
            LocalDate.now().minus(2, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 2,
            LocalDate.now().minus(2, ChronoUnit.MONTHS),
            LocalDate.now().minus(1, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertTrue(result);
    }

    @Test
    public void it_should_return_true_if_second_project_end_date_is_equal_to_first_project_start_date() {
        Assignment assignment1 = new Assignment(1, 2,
            LocalDate.now().minus(3, ChronoUnit.MONTHS),
            LocalDate.now().minus(2, ChronoUnit.MONTHS)
        );

        Assignment assignment2 = new Assignment(2, 2,
            LocalDate.now().minus(5, ChronoUnit.MONTHS),
            LocalDate.now().minus(3, ChronoUnit.MONTHS)
        );

        boolean result = collaborationHelper.isCollaboration(assignment1, assignment2);

        assertTrue(result);
    }

}
