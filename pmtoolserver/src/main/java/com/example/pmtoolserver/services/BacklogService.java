package com.example.pmtoolserver.services;

import com.example.pmtoolserver.models.Backlog;
import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.models.ProjectTask;
import com.example.pmtoolserver.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BacklogService {

    private final ProjectRepository projectRepository;

    public Project addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        Backlog backlog = project.getBacklog();
        project.setBacklog(backlog);
        Integer projectTaskSequence = backlog.getProjectTaskSequence();
        projectTaskSequence++;
        backlog.setProjectTaskSequence(projectTaskSequence);
        projectTask.setProjectSequence(projectIdentifier+ "-" + projectTaskSequence);
        if(projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }
        if(projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }
        List<ProjectTask> projectTasks = backlog.getProjectTasks();
        projectTasks.add(projectTask);
        backlog.setProjectTasks(projectTasks);
        return projectRepository.save(project);

    }

}
