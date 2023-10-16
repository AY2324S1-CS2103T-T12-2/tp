package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;

import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Represents a list of events in the address book. Operations for managing the list of events are implemented here.
 */
public class EventList {

    private ArrayList<Event> events = new ArrayList<>();

    /**
     * Returns the list of events.
     * @return ArrayList of events.
     */
    public ArrayList<Event> getEventList() {
        return this.events;
    }

    /**
     * Adds an event to the list of events.
     * @param event Event to be added.
     */
    public void addEvent(Event event) {
        requireNonNull(event);
        this.events.add(event);
    }


    /**
     * Deletes an event from the list of events.
     * @param index index of the event to be deleted.
     */
    public void deleteEvent(int index) {
        return;
    }

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * @param target event to be edited. {@code target} must exist in the address book.
     * @param editedEvent event with the edited details.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        int index = this.events.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException(); // Change to event exception
        }

        this.events.set(index, editedEvent);
    }

    public void setEvents(ArrayList<Event> newEvents) {
        this.events = newEvents;
    }

    public ArrayList<Event> getEventsList() {
        return this.events;
    }
}
