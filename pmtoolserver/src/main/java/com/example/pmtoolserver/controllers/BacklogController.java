package com.example.pmtoolserver.controllers;

import com.example.pmtoolserver.models.Backlog;
import com.example.pmtoolserver.models.ProjectTask;
import com.example.pmtoolserver.services.BacklogService;
import com.example.pmtoolserver.services.MapValidationErrorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/backlog")
public class BacklogController {

    private final MapValidationErrorService mapValidationErrorService;
    private final BacklogService backlogService;

    @PostMapping("/{projectId}")
    public ResponseEntity<?> createNewProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult bindingResult, @PathVariable String projectId) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if(errorMap != null) return errorMap;
        ProjectTask savedProjectTask = backlogService.addProjectTask(projectId, projectTask);
        return new ResponseEntity<>(savedProjectTask, HttpStatus.CREATED);
    }

    @GetMapping("/{backlogId}")
    public Backlog getProjectBacklog(@PathVariable String backlogId) {
        return backlogService.findBacklogById(backlogId);
    }

    @GetMapping("list/{projectId}")
    public List<ProjectTask> getProjectTasksList(@PathVariable String projectId) {
        return backlogService.findProjectTasksList(projectId);
    }
}
