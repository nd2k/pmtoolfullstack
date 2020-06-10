package com.example.pmtoolserver.services;

import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        Project createdProject = projectRepository.save(project);
        return createdProject;
    }

}
