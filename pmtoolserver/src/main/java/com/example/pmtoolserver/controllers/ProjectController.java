package com.example.pmtoolserver.controllers;

import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.models.ProjectTask;
import com.example.pmtoolserver.services.BacklogService;
import com.example.pmtoolserver.services.MapValidationErrorService;
import com.example.pmtoolserver.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService mapValidationErrorService;
    private final BacklogService backlogService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if(errorMap != null) return errorMap;
        Project createdProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<?> createNewProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult, @PathVariable String projectId) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if(errorMap != null) return errorMap;
        Project project = backlogService.addProjectTask(projectId, projectTask);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findByProjectIdentifier(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProject();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProjectById(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<>("Project with Id '" + projectId + "' was deleted", HttpStatus.OK);
    }
}
