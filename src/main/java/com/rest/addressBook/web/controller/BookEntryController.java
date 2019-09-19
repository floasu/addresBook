package com.rest.addressBook.web.controller;

import com.rest.addressBook.constants.InternationalizationConstants;
import com.rest.addressBook.model.BookEntry;
import com.rest.addressBook.service.interfaces.IBookEntryService;
import com.rest.addressBook.web.dto.BookEntryDto;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/rest/bookentry")
public class BookEntryController implements InternationalizationConstants {

    @Autowired
    private IBookEntryService bookEntryService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public List<BookEntryDto> listBookEntries(){
        return bookEntryService.getBookEntries().stream().map(BookEntryDto::new).collect(toList());
    }

    @PostMapping
    public ResponseEntity createBookEntry(@RequestBody BookEntryDto bookEntryDto) throws ParseException {
        BookEntry entity = new BookEntry();
        try{
            fill(bookEntryDto, entity);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        entity = bookEntryService.createEntry(entity);
        bookEntryDto.id = entity.getId();
        return ResponseEntity.ok(bookEntryDto);
    }

    @PutMapping("{id}")
    public ResponseEntity updateCourse(@PathVariable Long id,
                             @RequestBody BookEntryDto dto) throws ParseException {
        BookEntry entity = bookEntryService.getBookEntryById(id);
        try{
            fill(dto, entity);
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable Long id) {
        bookEntryService.deleteEntry(id);
    }

    private void fill(BookEntryDto sourceDto, BookEntry targetEntity) throws ParseException {
        if(StringUtils.isBlank(sourceDto.firstName)) {
            throw new IllegalArgumentException(getErrorMessage(FIRSTNAME_MANDATORY));
        }
        targetEntity.setFirstName(sourceDto.firstName);

        if(StringUtils.isBlank(sourceDto.lastName)) {
            throw new IllegalArgumentException(getErrorMessage(LASTTNAME_MANDATORY));
        }
        targetEntity.setLastName(sourceDto.lastName);

        if (StringUtils.isBlank(sourceDto.phoneNumber)) {
            throw new IllegalArgumentException(getErrorMessage(PHONE_NUMBER_MANDATORY));
        }
        targetEntity.setPhoneNumber(sourceDto.phoneNumber);

        if (StringUtils.isBlank(sourceDto.address)) {
            throw new IllegalArgumentException(getErrorMessage(ADDRESS_MANDATORY));
        }
        targetEntity.setPhoneNumber(sourceDto.address);

        if (StringUtils.isBlank(sourceDto.emailAddress)) {
            throw new IllegalArgumentException(getErrorMessage(EMAIL_ADDRESS_MANDATORY));
        }

        if (!EmailValidator.getInstance().isValid(sourceDto.emailAddress)) {
            throw new IllegalArgumentException(getErrorMessage(EMAIL_ADDRESS_INVALID));
        }
        targetEntity.setEmailAddress(sourceDto.emailAddress);

    }

    protected  String getErrorMessage(String errorKey){
       return messageSource.getMessage(errorKey, null, "NOT_FOUND", request.getLocale());
    }

}
