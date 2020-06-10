package com.example.pmtoolserver.repositories;


import com.example.pmtoolserver.models.Project;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, ObjectId> {

    @Override
    Iterable<Project> findAllById(Iterable<ObjectId> iterable);
}
