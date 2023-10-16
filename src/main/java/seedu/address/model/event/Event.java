package seedu.address.model.event;


import static seedu.address.model.event.EventTime.NULL_EVENT_TIME;

import java.util.ArrayList;
import java.util.Optional;

import seedu.address.model.person.Person;

/**
 * Represents an Event in the address book.
 */
public abstract class Event {

    private final ArrayList<Person> persons = new ArrayList<>();

    private EventDate startDate;

    private Optional<EventTime> startTime;
    private EventDate endDate;

    private Optional<EventTime> endTime;

    private EventName name;

    private EventType eventType;

    /**
     * Constructor for eventS with optional start and end time
     * @param name name of the event
     * @param startDate start date of the event
     * @param endDate  end date of the event
     */
    public Event(EventName name, EventDate startDate, EventDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    /**
     * Constructor for eventS with optional start and end time
     * @param name name of the event
     * @param startDate start date of the event
     * @param startTime start time of the event
     * @param endDate  end date of the event
     * @param endTime end time of the event
     */
    public Event(EventType eventType, EventName name, EventDate startDate, Optional<EventTime> startTime,
                 EventDate endDate, Optional<EventTime> endTime) {

        this.eventType = eventType;
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;

    }


    public EventType getEventType() {
        return this.eventType;
    }

    /**
     * Gets the start date time of the event
     * @return start date time of the event
     */
    public EventDate getStartDate() {
        return this.startDate;
    }

    public EventTime getStartTime() {
        return this.startTime.get();
    }

    /**
     * Returns true if the event has a start time.
     */
    public boolean hasStartTime() {
        return !(this.startTime.get() == NULL_EVENT_TIME);
    }


    /**
     * Returns true if the event has an end time.
     * @return true if the event has an end time
     */
    public boolean hasEndTime() {
        return !(this.endTime.get() == NULL_EVENT_TIME);
    }

    public EventDate getEndDate() {
        return this.endDate;
    }


    public EventTime getEndTime() {
        return this.endTime.get();
    }

    /**
     * Gets the name of the event
     * @return name of the event
     */
    public EventName getName() {
        return this.name;
    }

    /**
     * Returns true if both events are of the same type and have the same name.
     * @param event event to be compared
     * @return true if both events are of the same type and have the same name
     */
    public abstract boolean isSameEvent(Event event);
}
