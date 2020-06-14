package com.example.pmtoolserver.repositories;


import com.example.pmtoolserver.models.Backlog;
import com.example.pmtoolserver.models.Project;
import com.example.pmtoolserver.models.ProjectTask;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, ObjectId> {

    Project findByProjectIdentifier(String projectId);

    @Override
    List<Project> findAll();

//    @Query("{ 'backlog.backlogIdentifier': ?0 }")
//    Backlog findBacklog_BacklogIdentifier(String backlogId);
//
//    @Query("{ 'backlog' : { 'projectTasksList' : { 'projectTaskIdentifier': ?0 }}}")
//    List<ProjectTask> findByBacklog_ProjectTasksList_ProjectTaskIdentifier(String backlogId);

}
