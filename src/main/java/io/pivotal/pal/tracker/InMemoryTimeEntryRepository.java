package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long,TimeEntry> timeEntryMap = new HashMap();
    private Long nbr = 0L;

    public TimeEntry create(TimeEntry timeEntry) {
        nbr++;

        TimeEntry timeEntryToCreate = new TimeEntry(nbr, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntryMap.put(nbr, timeEntryToCreate);

        return timeEntryToCreate;
    }

    public TimeEntry find(Long id) {
        return timeEntryMap.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntryMap.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {

        TimeEntry timeEntryToUpdate = null;
        if (timeEntryMap.containsKey(id))
        {
            timeEntryToUpdate = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
            timeEntryMap.put(id, timeEntryToUpdate);
        }
        return timeEntryToUpdate;

    }

    public void delete(Long id) {
        timeEntryMap.clear();
    }
}
