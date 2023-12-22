package com.academy.sirma.bff.employees.services;

import com.academy.sirma.bff.employees.entities.CollaborationEntity;
import com.academy.sirma.bff.employees.mappers.CollaborationMapper;
import com.academy.sirma.bff.employees.models.CollaborationWrapper;
import com.academy.sirma.bff.employees.models.CollaborativeWork;
import com.academy.sirma.bff.employees.models.EmployeePair;
import com.academy.sirma.bff.employees.repositories.CollaborationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CollaborationCrudService {

    private final CollaborationRepository collaborationRepository;

    private final CollaborationMapper collaborationMapper;

    @Autowired
    public CollaborationCrudService(CollaborationRepository collaborationRepository,
                                    CollaborationMapper collaborationMapper) {
        this.collaborationRepository = collaborationRepository;
        this.collaborationMapper = collaborationMapper;
    }

    public void deleteAll() {
        collaborationRepository.deleteAll();
    }

    public List<CollaborationEntity> saveAll(Map<EmployeePair, CollaborativeWork> collaborations) {
        List<CollaborationEntity> entitiesToBeSaved = collaborationMapper.toListOfCollaborationEntity(collaborations);
        return collaborationRepository.saveAll(entitiesToBeSaved);
    }

    public List<CollaborationWrapper> findAllCollaborations() {
        List<CollaborationEntity> collaborationEntities = collaborationRepository.findAll();

        List<CollaborationWrapper> collaborations = collaborationMapper.toListOfCollaborationWrapper(collaborationEntities);

        return collaborations;
    }
}
