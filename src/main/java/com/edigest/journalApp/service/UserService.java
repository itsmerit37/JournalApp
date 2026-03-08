package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.UserEntry;
import com.edigest.journalApp.repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    // Single shared encoder instance
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Encodes the password before saving.
     */
    public void saveEntry(UserEntry userEntry) {
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntryRepository.save(userEntry);
    }

    public List<UserEntry> getAll() {
        return userEntryRepository.findAll();
    }

    public Optional<UserEntry> findById(ObjectId id) {
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userEntryRepository.deleteById(id);
    }

    public UserEntry findByUsername(String username) {
        return userEntryRepository.findByUsername(username);
    }
}