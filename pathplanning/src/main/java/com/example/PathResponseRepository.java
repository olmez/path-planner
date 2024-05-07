package com.example;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PathResponseRepository
    extends MongoRepository<PathResponseEntity, String> {}
