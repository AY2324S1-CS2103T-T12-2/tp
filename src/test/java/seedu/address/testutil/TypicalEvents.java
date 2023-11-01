package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Meeting TP_MEETING;

    static {
        try {
            TP_MEETING = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting TP_MEETING_WITH_PERSONS;

    static {
        try {
            TP_MEETING_WITH_PERSONS = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting TP_MEETING_WITH_GROUPS;

    static {
        try {
            TP_MEETING_WITH_GROUPS = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .withGroups("friends", "owesMoney")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static final Meeting TP_MEETING_LATER_START_TIME;

    static {
        try {
            TP_MEETING_LATER_START_TIME = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0100")
                    .withEventEndTime("2359")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting TP_MEETING_EARLIER_END_TIME;

    static {
        try {
            TP_MEETING_EARLIER_END_TIME = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withEventEndTime("2358")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_WITHOUT_TIME;

    static {
        try {
            MEETING_WITHOUT_TIME = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_WITHOUT_START_TIME;

    static {
        try {
            MEETING_WITHOUT_START_TIME = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventEndTime("2359")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_WITHOUT_END_TIME;

    static {
        try {
            MEETING_WITHOUT_END_TIME = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_WITHOUT_PERSONS;

    static {
        try {
            MEETING_WITHOUT_PERSONS = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-18")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_LATER_THAN_TP_MEETING;

    static {
        try {
            MEETING_LATER_THAN_TP_MEETING = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate("2023-10-19")
                    .withEventStartTime("0000")
                    .withEventEndTime("2359")
                    .withPerson("Alice Pauline", "Benson Meier")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_3_DAYS_AFTER_TODAY;

    static {
        try {
            MEETING_3_DAYS_AFTER_TODAY = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate(LocalDate.now().plusDays(3).toString())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Meeting MEETING_6_DAYS_AFTER_TODAY;

    static {
        try {
            MEETING_6_DAYS_AFTER_TODAY = new MeetingBuilder().withEventName("TP MEETING TEST")
                    .withEventDate(LocalDate.now().plusDays(6).toString())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
