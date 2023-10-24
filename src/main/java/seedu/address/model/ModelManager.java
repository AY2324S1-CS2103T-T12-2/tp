package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.event.Meeting;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    private final FilteredList<Event> events;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        this.events = new FilteredList<>(this.addressBook.getEventList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    // ========== Event ======================================================================================

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        this.addressBook.setEvent(target, editedEvent);
    }

    @Override
    public void deleteEvent(Event target) {
        this.addressBook.deleteEvent(target);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return this.filteredPersons;
    }

    /**
     * Returns the list of events
     * @return ArrayList of events
     */
    @Override
    public ObservableList<Event> getEventList() {
        return this.events;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        this.filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        this.events.setPredicate(predicate);
    }

    /**
     * Adds an event to the address book.
     * @param toAdd Event to be added.
     */
    @Override
    public void addEvent(Event toAdd) {
        addressBook.addEvent(toAdd);

    }

    @Override
    public Set<Name> findInvalidNames(Set<Name> names) {
        Set<Name> invalidNames = new HashSet<>();

        for (Name name : names) {
            boolean hasName = checkNameExists(name);

            if (!hasName) {
                invalidNames.add(name);
            }
        }

        return invalidNames;
    }

    @Override
    public void updateAssignedPersons(Person personToEdit, Person editedPerson) {
        for (Event event : this.events) {
            if (event.getNames().contains(personToEdit.getName())) {
                setEvent(event, createUpdatedEvent(event, personToEdit, editedPerson));
                event.getNames().add(editedPerson.getName());
            }
        }
    }

    @Override
    public void updateAssignedPersons(Person personToDelete) {
        for (Event event : this.events) {
            if (event.getNames().contains(personToDelete.getName())) {
                event.getNames().remove(personToDelete.getName());
                setEvent(event, event); //update event in the storage
            }
        }
    }

    private Event createUpdatedEvent(Event event, Person personToEdit, Person editedPerson) {

        //add other switch statements for future event types
        switch (event.getEventType().toString()) {
        default:
            return new Meeting(event.getName(), event.getStartDate(),
                    Optional.of(event.getStartTime()), Optional.of(event.getEndTime()),
                    event.getUpdatedNames(personToEdit.getName(), editedPerson.getName()));
        }
    }

    private boolean checkNameExists(Name name) {
        for (Person person : this.filteredPersons) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return this.addressBook.equals(otherModelManager.addressBook)
                && this.userPrefs.equals(otherModelManager.userPrefs)
                && this.filteredPersons.equals(otherModelManager.filteredPersons);
    }
}
