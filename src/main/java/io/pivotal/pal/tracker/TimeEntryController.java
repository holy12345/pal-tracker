package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry entry = this.timeEntryRepository.create(timeEntry);
        return new ResponseEntity(entry, null, HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry entry = this.timeEntryRepository.find(id);
        ResponseEntity<TimeEntry> responseEntity;
        if (entry == null) {
            responseEntity = new ResponseEntity(entry, null, HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(entry, null, HttpStatus.OK);
        }
        return responseEntity;

    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = this.timeEntryRepository.list();
        return new ResponseEntity(timeEntryList, null, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry entry = this.timeEntryRepository.update(id, timeEntry);
        ResponseEntity<TimeEntry> responseEntity;
        if (entry == null) {
            responseEntity = new ResponseEntity(entry, null, HttpStatus.NOT_FOUND);
        } else {
            responseEntity = new ResponseEntity(entry, null, HttpStatus.OK);
        }

        return responseEntity;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        this.timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
