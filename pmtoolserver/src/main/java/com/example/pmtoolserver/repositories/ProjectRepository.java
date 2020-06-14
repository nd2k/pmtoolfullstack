package com.example.pmtoolserver.repositories;

import com.example.pmtoolserver.models.Project;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends MongoRepository<Project, ObjectId> {

    Project findByProjectIdentifier(String projectId);

    @Override
    List<Project> findAll();

    Project findByBacklog_BacklogIdentifier(String backlogId);

    Project findByBacklog_ProjectTasksList_ProjectTaskIdentifier(String backlogId);

    Project findByBacklog_ProjectTasksList_ProjectSequence(String projectSequence);

}
