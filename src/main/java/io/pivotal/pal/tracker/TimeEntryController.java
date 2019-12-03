package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry entry = timeEntryRepository.create(timeEntryToCreate);
        ResponseEntity res = new ResponseEntity(entry , HttpStatus.CREATED);
        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable  Long id) {

        if(null == id)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        TimeEntry entry = timeEntryRepository.find(id);
        ResponseEntity res;

        if (entry != null)
        {
            res = new ResponseEntity(entry, HttpStatus.OK);
        }
        else
        {
            res = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return res;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {

        List<TimeEntry> entries = timeEntryRepository.list();
        ResponseEntity res = new ResponseEntity(entries, HttpStatus.OK);
        return res;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry entry) {

        ResponseEntity res;
        TimeEntry result = timeEntryRepository.update(id, entry);

        if (result != null)
            res = new ResponseEntity(result, HttpStatus.OK);
        else
            res = new ResponseEntity(HttpStatus.NOT_FOUND);

        return res;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        timeEntryRepository.delete(id);
        ResponseEntity res = new ResponseEntity(HttpStatus.NO_CONTENT);
        return res;
    }
}
