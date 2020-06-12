package com.example.pmtoolserver.repositories;

import com.example.pmtoolserver.models.Backlog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BacklogRepository extends MongoRepository<Backlog, ObjectId> {
}
