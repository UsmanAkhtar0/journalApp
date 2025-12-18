package com.engineeringdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.engineeringdigest.journalApp.entity.JournalEntry;
import com.engineeringdigest.journalApp.entity.User;
import com.engineeringdigest.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional // Works on Atlas or Replica in Local Mongo
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            // System.out.println(saved);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry. " + e);
        }
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public Boolean deleteById(ObjectId id, String username) {
        Boolean isRemoved = false;
        try {
            User user = userService.findByUserName(username);
            isRemoved = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (isRemoved) {
                userService.saveEntry(user);
                journalEntryRepository.deleteById(id);
                return isRemoved;
            }
            return isRemoved;
        } catch (Exception e) {
            System.out.println("Error occured: " + e);
            throw new RuntimeException("An error occured while deleting the entry: " + e);
        }
    }

    public User findByUserName(String username) {
        return userService.findByUserName(username);
    }
}
