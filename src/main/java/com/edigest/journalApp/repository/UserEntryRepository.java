package com.edigest.journalApp.repository;

import com.edigest.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepository extends MongoRepository<UserEntry, ObjectId> {

    // Fixed: was findByUserName (capital N) — must match the field name 'username' exactly
    UserEntry findByUsername(String username);
}