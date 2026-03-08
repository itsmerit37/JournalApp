package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.UserEntry;
import com.edigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntry> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntry userEntry) {
        userService.saveEntry(userEntry);
        return ResponseEntity.ok("User " + userEntry.getUsername() + " created successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserEntry userEntry) {
        UserEntry userInDB = userService.findByUsername(userEntry.getUsername());
        if (userInDB != null) {
            userInDB.setPassword(userEntry.getPassword());
            userService.saveEntry(userInDB);
            return ResponseEntity.ok("User " + userEntry.getUsername() + " updated successfully");
        } else {
            return ResponseEntity.badRequest().body("User " + userEntry.getUsername() + " not found");
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        UserEntry userInDB = userService.findByUsername(username);
        if (userInDB != null) {
            userService.deleteById(userInDB.getId());
            return ResponseEntity.ok("User " + username + " deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("User " + username + " not found");
        }
    }
}