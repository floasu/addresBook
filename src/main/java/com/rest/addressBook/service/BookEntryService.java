package com.rest.addressBook.service;

import com.rest.addressBook.model.BookEntry;
import com.rest.addressBook.repository.IBookEntryRepository;
import com.rest.addressBook.service.interfaces.IBookEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookEntryService implements IBookEntryService {

    @Autowired
    IBookEntryRepository bookEntryRepository;

    @Override
    public BookEntry getBookEntryById(long id) {
        return bookEntryRepository.findById(id).get();
    }

    @Override
    public List<BookEntry> getBookEntries() {
        return bookEntryRepository.findAll();
    }

    public BookEntry createEntry(BookEntry newEntry){
        return bookEntryRepository.save(newEntry);
    }

    @Override
    public BookEntry updateEntry(BookEntry entryToUpdate) {
        return bookEntryRepository.save(entryToUpdate);
    }

    @Override
    public void deleteEntry(long entryId) {
        bookEntryRepository.deleteById(entryId);
    }

}
