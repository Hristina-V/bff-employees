package com.academy.sirma.bff.employees.controllers;

import com.academy.sirma.bff.employees.models.Assignment;
import com.academy.sirma.bff.employees.models.CollaborativeWork;
import com.academy.sirma.bff.employees.models.EmployeePair;
import com.academy.sirma.bff.employees.services.AssignmentService;
import com.academy.sirma.bff.employees.services.CollaborationAggregatorService;
import com.academy.sirma.bff.employees.services.CollaborationCrudService;
import com.academy.sirma.bff.employees.services.CollaborationService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class AssignmentsController {

    private final AssignmentService assignmentService;

    private final CollaborationService collaborationService;

    private final CollaborationAggregatorService collaborationAggregatorService;

    private final CollaborationCrudService collaborationCrudService;

    @Autowired
    public AssignmentsController(CollaborationService collaborationService,
                                 AssignmentService assignmentService,
                                 CollaborationAggregatorService collaborationAggregatorService,
                                 CollaborationCrudService collaborationCrudService) {
        this.collaborationService = collaborationService;
        this.assignmentService = assignmentService;
        this.collaborationAggregatorService = collaborationAggregatorService;
        this.collaborationCrudService = collaborationCrudService;
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
        collaborationAggregatorService.aggregate(collaborations);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employees-assignments/top/{n}")
    public List<CollaborativeWork> getTopNCollaborativeEmployees(@PathVariable int n) {
        Map<EmployeePair, CollaborativeWork> collaborations = collaborationCrudService.findAllCollaborations();

        return collaborationService.findEmployeesWithMostCollaborationDays(collaborations, n);
    }
}
