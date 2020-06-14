package com.example.pmtoolserver.repositories;

import com.example.pmtoolserver.models.Backlog;
import com.example.pmtoolserver.models.ProjectTask;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomMongoOperations {

    Backlog findByBacklogId(String backlogId);

    List<ProjectTask> findProjectTasksListById(String projectId);
}
