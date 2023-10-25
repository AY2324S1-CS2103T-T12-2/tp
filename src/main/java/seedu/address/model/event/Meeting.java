package seedu.address.model.event;

import java.util.Optional;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.person.Name;


/**
 * Represents a Meeting in the address book.
 */
public class Meeting extends Event {

    private static final String MEETING_EVENT_TYPE = "meeting";

    /**
     * Constructor for the meeting with optional start and end time
     * @param name name of the meeting
     * @param date date of the meeting
     * @param startTime start time of the meeting
     * @param endTime end time of the meeting
     * @param names names of the people attending the meeting
     */
    public Meeting(EventName name, EventDate date, Optional<EventTime> startTime,
                   Optional<EventTime> endTime, Set<Name> names, Set<Group> groups) {
        super(new EventType(MEETING_EVENT_TYPE), name, date, startTime, date, endTime, names, groups);
    }

    /**
     * ToString for the meeting
     */
    @Override
    public String toString() {
        return String.format(
                "%1$s; Date: %2$s; Start_Time: %3$s; End_Time: %4$s; Assigned_Persons: %5$s; Assigned_Groups: %6$s;",
                super.getName().toString(), super.getStartDate().toString(),
                super.getStartTime().toString(), super.getEndTime().toString(), super.getNames(),
                super.getGroups());
    }

    /**
     * Checks if the meeting is the same as another meeting
     * @param other the other meeting to be compared to
     * @return true if the meetings have the same name.
     *
     */
    @Override
    public boolean isSameEvent(Event other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Meeting)) {
            return false;
        }
        return other.getName().equals(getName());
    }
}
