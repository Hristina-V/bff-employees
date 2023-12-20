package com.academy.sirma.bff.employees.controllers;

import com.academy.sirma.bff.employees.models.Assignment;
import com.academy.sirma.bff.employees.models.CollaborativeWork;
import com.academy.sirma.bff.employees.models.EmployeePair;
import com.academy.sirma.bff.employees.services.AssignmentService;
import com.academy.sirma.bff.employees.services.CollaborationService;
import com.academy.sirma.bff.employees.services.csv.аssignments.AssignmentsCsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class AssignmentsController {

    private final CollaborationService collaborationService;

    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentsController(CollaborationService collaborationService, AssignmentService assignmentService) {
        this.collaborationService = collaborationService;
        this.assignmentService = assignmentService;
    }

    @PostMapping("/employees-assignments/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) {
        assignmentService.save(file);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/employees-assignments/aggregate")
    public ResponseEntity<Void> aggregate() {
        List<Assignment> assignments = assignmentService.findAll();
        Map<EmployeePair, CollaborativeWork> collaborations = collaborationService.findCollaborations(assignments);
        // TODO Save collaborations to DB
        EmployeePair targetPair = collaborationService.findEmployeesWithMostCollaborationDays(collaborations);
        CollaborativeWork collaborativeWork = collaborations.get(targetPair);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/employees-assignments/top/{n}")
    public ResponseEntity<Void> getTopNCollaborativeEmployees(@RequestParam int n) {
        // TODO READ collaborations from DB
        Map<EmployeePair, CollaborativeWork> collaborations = null;

        if(n == 1) {
            EmployeePair targetPair = collaborationService.findEmployeesWithMostCollaborationDays(collaborations);
            CollaborativeWork collaborativeWork = collaborations.get(targetPair);
            return null; //TODO define actual response format
        } else {
            //TODO implement more generic logic that is able to find top N collaborative employees
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
