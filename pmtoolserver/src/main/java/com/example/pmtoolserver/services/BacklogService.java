package com.example.pmtoolserver.services;

import com.example.pmtoolserver.exceptions.ProjectNotFoundException;
import com.example.pmtoolserver.models.Backlog;
import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.models.ProjectTask;
import com.example.pmtoolserver.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BacklogService {

    private final ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        try {
            Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
            Backlog backlog = project.getBacklog();
            project.setBacklog(backlog);
            Integer projectTaskSequence = backlog.getProjectTaskSequence();
            projectTaskSequence++;
            backlog.setProjectTaskSequence(projectTaskSequence);
            projectTask.setProjectTaskIdentifier(projectIdentifier);
            projectTask.setProjectSequence(projectIdentifier + "-" + projectTaskSequence);
            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            if (projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }
            List<ProjectTask> projectTasks;
            if (backlog.getProjectTasksList() == null) {
                projectTasks = new ArrayList<>();
            } else {
                projectTasks = backlog.getProjectTasksList();
            }
            projectTasks.add(projectTask);
            backlog.setProjectTasksList(projectTasks);
            projectRepository.save(project);
            return projectTask;
        } catch (Exception ex) {
            throw new ProjectNotFoundException("Project not found");
        }

    }

    public Backlog findBacklogById(String backlogId) {
        Project project = projectRepository.findByBacklog_BacklogIdentifier(backlogId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with id '" + backlogId + "' does not exist");
        }
        return project.getBacklog();
    }

    public List<ProjectTask> findProjectTasksList(String projectId) {
        Project project = projectRepository.findByBacklog_ProjectTasksList_ProjectTaskIdentifier(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Project with id '" + projectId + "' does not exist");
        }
        return project.getBacklog().getProjectTasksList();
    }
}
