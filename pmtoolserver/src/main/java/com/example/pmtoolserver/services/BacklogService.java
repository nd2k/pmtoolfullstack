package com.example.pmtoolserver.services;

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
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        Backlog backlog = project.getBacklog();
        project.setBacklog(backlog);
        Integer projectTaskSequence = backlog.getProjectTaskSequence();
        projectTaskSequence++;
        backlog.setProjectTaskSequence(projectTaskSequence);
        projectTask.setProjectTaskIdentifier(projectIdentifier);
        projectTask.setProjectSequence(projectIdentifier+ "-" + projectTaskSequence);
        if(projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }
        if(projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }
        List<ProjectTask> projectTasks;
        if(backlog.getProjectTasksList() == null) {
            projectTasks = new ArrayList<>();
        } else {
            projectTasks = backlog.getProjectTasksList();
        }
        projectTasks.add(projectTask);
        backlog.setProjectTasksList(projectTasks);
        projectRepository.save(project);
        return projectTask;

    }

    public Backlog findBacklogById(String backlogId) {
        Project project = projectRepository.findByBacklog_BacklogIdentifier(backlogId);
        return project.getBacklog();
    }

    public List<ProjectTask> findProjectTasksList(String projectId) {
        Project project = projectRepository.findByBacklog_ProjectTasksList_ProjectTaskIdentifier(projectId);
        return project.getBacklog().getProjectTasksList();
    }
}
