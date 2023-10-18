package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.group.Group;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{new Person(new Name("Alex Yeoh"),
            Optional.of(new Phone("87438807")),
            Optional.of(new Email("alexyeoh@example.com")),
            Optional.of(new Address("Blk 30 Geylang Street 29, #06-40")),
            Optional.of(new Birthday("2001-12-20")), getGroupSet("friends")),
            new Person(new Name("Bernice Yu"),
            Optional.of(new Phone("99272758")),
            Optional.of(new Email("berniceyu@example.com")),
            Optional.of(new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18")),
            Optional.of(new Birthday("2001-12-21")),
            getGroupSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"),
            Optional.of(new Phone("93210283")),
            Optional.of(new Email("charlotte@example.com")),
            Optional.of(new Address("Blk 11 Ang Mo Kio Street 74, #11-04")),
            Optional.of(new Birthday("2001-12-22")),
            getGroupSet("neighbours")),
            new Person(new Name("David Li"),
            Optional.of(new Phone("91031282")),
            Optional.of(new Email("lidavid@example.com")),
            Optional.of(new Address("Blk 436 Serangoon Gardens Street 26, #16-43")),
            Optional.of(new Birthday("2001-12-23")),
            getGroupSet("family")),
            new Person(new Name("Irfan Ibrahim"),
            Optional.of(new Phone("92492021")),
            Optional.of(new Email("irfan@example.com")),
            Optional.of(new Address("Blk 47 Tampines Street 20, #17-35")),
            Optional.of(new Birthday("2001-12-24")),
            getGroupSet("classmates")),
            new Person(new Name("Roy Balakrishnan"),
            Optional.of(new Phone("92624417")),
            Optional.of(new Email("royb@example.com")),
            Optional.of(new Address("Blk 45 Aljunied Street 85, #11-31")),
            Optional.of(new Birthday("2001-12-25")),
            getGroupSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a group set containing the list of strings given.
     */
    public static Set<Group> getGroupSet(String... strings) {
        return Arrays.stream(strings)
                .map(Group::new)
                .collect(Collectors.toSet());
    }

    public static Set<Name> getNameSet(String... strings) {
        return Arrays.stream(strings)
                .map(Name::new)
                .collect(Collectors.toSet());
    }
}
