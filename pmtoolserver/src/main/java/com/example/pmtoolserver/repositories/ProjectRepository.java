package com.example.pmtoolserver.repositories;


import com.example.pmtoolserver.models.Project;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, ObjectId> {

    Project findByProjectIdentifier(String projectId);

    @Override
    List<Project> findAll();
}
