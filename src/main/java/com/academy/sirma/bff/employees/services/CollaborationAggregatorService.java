package com.academy.sirma.bff.employees.services;

import com.academy.sirma.bff.employees.entities.CollaborationEntity;
import com.academy.sirma.bff.employees.mappers.CollaborationMapper;
import com.academy.sirma.bff.employees.models.*;
import com.academy.sirma.bff.employees.repositories.CollaborationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CollaborationAggregatorService {

    private final CollaborationCrudService collaborationCrudService;

    private final CollaborationMapper collaborationMapper;

    @Autowired
    public CollaborationAggregatorService(CollaborationCrudService collaborationCrudService,
                                          CollaborationMapper collaborationMapper) {
        this.collaborationCrudService = collaborationCrudService;
        this.collaborationMapper = collaborationMapper;
    }

    public void aggregate(Map<EmployeePair, CollaborativeWork> collaborations) {
        // We delete all records, since we're currently making no difference across different runs.
        collaborationCrudService.deleteAll();

        collaborationCrudService.saveAll(collaborations);
    }
}
