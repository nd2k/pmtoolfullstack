package com.example.pmtoolserver.repositories;

import com.example.pmtoolserver.models.Backlog;
import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.models.ProjectTask;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CustomMongoOperationsImpl implements CustomMongoOperations {

    private final MongoTemplate mongoTemplate;

    @Override
    public Backlog findByBacklogId(String backlogId) {
        Project project = mongoTemplate.findOne(new Query(Criteria.where("backlog.backlogIdentifier").is(backlogId)), Project.class);
        return project.getBacklog();
    }

    @Override
    public List<ProjectTask> findProjectTasksListById(String projectId) {
        Project project = mongoTemplate.findOne(new Query(Criteria.where("projectIdentifier").is(projectId)), Project.class);
        return project.getBacklog().getProjectTasksList();
    }
}
