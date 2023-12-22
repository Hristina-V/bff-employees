package com.academy.sirma.bff.employees.services;

import com.academy.sirma.bff.employees.entities.AssignmentEntity;
import com.academy.sirma.bff.employees.exceptions.ParseFIleException;
import com.academy.sirma.bff.employees.mappers.AssignmentMapper;
import com.academy.sirma.bff.employees.models.Assignment;
import com.academy.sirma.bff.employees.repositories.AssignmentRepository;
import com.academy.sirma.bff.employees.services.csv.аssignments.AssignmentsCsvFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    private final AssignmentsCsvFileReader assignmentsCsvFileReader;

    private final AssignmentMapper assignmentMapper;

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentService(AssignmentsCsvFileReader assignmentsCsvFileReader, AssignmentMapper assignmentMapper, AssignmentRepository assignmentRepository) {
        this.assignmentsCsvFileReader = assignmentsCsvFileReader;
        this.assignmentMapper = assignmentMapper;
        this.assignmentRepository = assignmentRepository;
    }

    public void save(MultipartFile file) {
        try {
            // We delete all records, since we're currently making no difference across different runs.
            assignmentRepository.deleteAll();

            List<Assignment> assignments = assignmentsCsvFileReader.readFromFile(file.getInputStream());
            List<AssignmentEntity> entities = assignmentMapper.toListOfEntities(assignments);
            assignmentRepository.saveAll(entities);
        } catch (IOException e) {
            throw new ParseFIleException(e);
        }
    }

    public List<Assignment> findAll() {
        List<AssignmentEntity> entities = assignmentRepository.findAll();
        return assignmentMapper.toListOfAssignments(entities);
    }

    public Assignment save(Assignment assignment) {
        AssignmentEntity assignmentEntity = assignmentMapper.toEntity(assignment);
        AssignmentEntity saved = assignmentRepository.save(assignmentEntity);

        return assignmentMapper.toAssignment(saved);
    }

    public List<Assignment> findByEmployee(long employeeId) {
        List<AssignmentEntity> entities = assignmentRepository.findAllByEmployeeId(employeeId);
        return assignmentMapper.toListOfAssignments(entities);
    }

    public List<Assignment> findByProject(long projectId) {
        List<AssignmentEntity> entities = assignmentRepository.findAllByProjectId(projectId);
        return assignmentMapper.toListOfAssignments(entities);
    }
}
