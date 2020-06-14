package com.example.pmtoolserver.services;

import com.example.pmtoolserver.exceptions.ProjectNotFoundException;
import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.models.ProjectTask;
import com.example.pmtoolserver.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectTaskService {

    private final ProjectRepository projectRepository;

    public ProjectTask findProjectTaskByProjectSequence(String backlogId, String projectSequence) {
        Project project = projectRepository.findByBacklog_BacklogIdentifier(backlogId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with id '" + backlogId + "' does not exist");
        }
        Project pj = projectRepository.findByBacklog_ProjectTasksList_ProjectSequence(projectSequence);
        if (pj == null) {
            throw new ProjectNotFoundException("Project Task with id '" + projectSequence + "' does not exist");
        }

        List<ProjectTask> projectTasksList = project.getBacklog().getProjectTasksList();
        ProjectTask projectTask = projectTasksList
                .stream()
                .filter(pt -> projectSequence.equals(pt.getProjectSequence()))
                .findAny()
                .orElseThrow(() -> new ProjectNotFoundException("Project Task with id '" + projectSequence + "' does not exist in this project"));
        return projectTask;
    }
}
