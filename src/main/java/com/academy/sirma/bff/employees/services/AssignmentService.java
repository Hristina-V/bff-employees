package com.academy.sirma.bff.employees.services;

import com.academy.sirma.bff.employees.entities.AssignmentEntity;
import com.academy.sirma.bff.employees.mappers.AssignmentMapper;
import com.academy.sirma.bff.employees.models.Assignment;
import com.academy.sirma.bff.employees.models.CollaborationPerAssignment;
import com.academy.sirma.bff.employees.models.CollaborationTimeFrame;
import com.academy.sirma.bff.employees.models.CollaborativeWork;
import com.academy.sirma.bff.employees.models.EmployeePair;
import com.academy.sirma.bff.employees.repositories.AssignmentsRepository;
import com.academy.sirma.bff.employees.services.csv.аssignments.AssignmentsCsvFileReader;
import com.academy.sirma.bff.employees.services.helpers.CollaborationHelper;
import com.academy.sirma.bff.employees.services.utils.LongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    private final AssignmentsCsvFileReader assignmentsCsvFileReader;

    private final AssignmentMapper assignmentMapper;

    private final AssignmentsRepository assignmentsRepository;

    @Autowired
    public AssignmentService(AssignmentsCsvFileReader assignmentsCsvFileReader, AssignmentMapper assignmentMapper, AssignmentsRepository assignmentsRepository) {
        this.assignmentsCsvFileReader = assignmentsCsvFileReader;
        this.assignmentMapper = assignmentMapper;
        this.assignmentsRepository = assignmentsRepository;
    }

    public void save(MultipartFile file) {
        try {
            List<Assignment>  assignments = assignmentsCsvFileReader.readFromFile(file.getInputStream());
            List<AssignmentEntity> entities = assignmentMapper.toListOfEntities(assignments);
            assignmentsRepository.saveAll(entities);
        } catch (IOException e) {
            // TODO add proper handling
            throw new RuntimeException(e);
        }
    }

    public List<Assignment> findAll() {
        List<AssignmentEntity> entities = assignmentsRepository.findAll();
        return assignmentMapper.toListOfAssignments(entities);
    }

//    public UserResponse findById(long id) {
//        Employee employee = userRepository.findById(id)
//            .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id + " found"));
//
//        UserResponse userResponse = userMapper.toUserResponse(employee);
//
//        return userResponse;
//    }
}
