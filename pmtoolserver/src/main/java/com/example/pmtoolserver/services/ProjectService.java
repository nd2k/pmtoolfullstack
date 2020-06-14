package com.example.pmtoolserver.services;

import com.example.pmtoolserver.exceptions.ProjectIdException;
import com.example.pmtoolserver.models.Backlog;
import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            if(project.getId() == null) {
                Backlog backlog = new Backlog();
                backlog.setBacklogIdentifier(project.getProjectIdentifier());
                project.setBacklog(backlog);
            }
            if(project.getId() != null ) {
                Project savedProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
                Backlog savedBacklog = savedProject.getBacklog();
                project.setBacklog(savedBacklog);
            }
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier() + "' already exists");
        }
    }

    public Project findByProjectIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project == null) {
            throw new ProjectIdException("Project Id '" + projectId + "' does not exist");
        }
        return project;
    }

    public Iterable<Project> findAllProject() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId);
        if(project == null) {
            throw new ProjectIdException("Cannot delete project with id '" + projectId + "'");
        }
        projectRepository.delete(project);
    }
}
