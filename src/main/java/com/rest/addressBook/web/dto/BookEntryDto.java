package com.rest.addressBook.web.dto;

import com.rest.addressBook.model.BookEntry;

public class BookEntryDto {

    public long id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address;
    public String emailAddress;

    public BookEntryDto(){

    }

    public BookEntryDto(BookEntry entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        phoneNumber = entity.getPhoneNumber();
        address = entity.getAddress();
        emailAddress = entity.getEmailAddress();
    }

}
