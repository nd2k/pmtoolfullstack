package com.example.pmtoolserver.controllers;

import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
        Project createdProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }
}
