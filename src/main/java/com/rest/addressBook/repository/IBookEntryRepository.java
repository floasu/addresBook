package com.rest.addressBook.repository;

import com.rest.addressBook.model.BookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookEntryRepository extends JpaRepository<BookEntry, Long> {


}
