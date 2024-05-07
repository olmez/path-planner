package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PathRequestRepository
    extends MongoRepository<PathRequestEntity, String> {}
