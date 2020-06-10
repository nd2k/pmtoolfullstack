package com.example.pmtoolserver.controllers;

import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/project")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMapping = new HashMap<>();
            for(FieldError error: bindingResult.getFieldErrors()) {
                errorMapping.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMapping, HttpStatus.BAD_REQUEST);
        }
        Project createdProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }
}
