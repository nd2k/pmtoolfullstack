package com.example.pmtoolserver.services;

import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }

}
