package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry) {
         journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
         journalEntryRepository.deleteById(id);
    }

        public JournalEntry updateEntry(ObjectId id, JournalEntry updateEntry) {

            Optional<JournalEntry> existingEntryOpt = journalEntryRepository.findById(id);

            if (existingEntryOpt.isPresent()) {

                JournalEntry existingEntry = existingEntryOpt.get();

                if (updateEntry.getTitle() != null && !updateEntry.getTitle().isEmpty()) {
                    existingEntry.setTitle(updateEntry.getTitle());
                }

                if (updateEntry.getContent() != null && !updateEntry.getContent().isEmpty()) {
                    existingEntry.setContent(updateEntry.getContent());
                }

                if (updateEntry.getDate() != null) {
                    existingEntry.setDate(updateEntry.getDate());
                }

                return journalEntryRepository.save(existingEntry);
            }

            return null;
        }
}
