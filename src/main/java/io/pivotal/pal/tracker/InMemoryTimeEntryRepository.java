package io.pivotal.pal.tracker;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private List<TimeEntry> timeEntryList = new ArrayList<>();

    private long index = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        index++;
        timeEntry.setId(index);
        this.timeEntryList.add(timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        for (TimeEntry t : timeEntryList) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        for (TimeEntry t : timeEntryList) {
            if (t.getId() == id) {
                t.setProjectId(timeEntry.getProjectId());
                t.setDate(timeEntry.getDate());
                t.setHours(timeEntry.getHours());
                t.setUserId(timeEntry.getUserId());
                return t;
            }
        }
        return null;
    }

    @Override
    public List<TimeEntry> list() {
        return timeEntryList;
    }

    @Override
    public void delete(long id) {
        int index = 0;
        for (int i = 0; i < timeEntryList.size(); i++) {
            if (timeEntryList.get(i).getId() == id) {
                index = i;
            }
        }
        timeEntryList.remove(index);
    }

}
