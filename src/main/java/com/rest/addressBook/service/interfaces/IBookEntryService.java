package com.rest.addressBook.service.interfaces;

import com.rest.addressBook.model.BookEntry;

import java.util.List;

public interface IBookEntryService {

    BookEntry getBookEntryById(long id);

    List<BookEntry> getBookEntries();

    BookEntry createEntry(BookEntry newEntry);

    BookEntry updateEntry(BookEntry entryToUpdate);

    void deleteEntry(long entryId);

}
