package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Optional;

import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.event.Meeting;

/**
 * Parses input arguments and creates a new AddMeetingCommand object
 * @author Yuheng
 */
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {


    @Override
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME);

        EventName meetingName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
        EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get());
        EventTime meetingStartTime = EventTime.NULL_EVENT_TIME;
        EventTime meetingEndTime = EventTime.NULL_EVENT_TIME;

        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            meetingStartTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_START_TIME).get());
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            meetingEndTime = ParserUtil.parseEventTime(argMultimap.getValue(PREFIX_END_TIME).get());
        }

        Meeting meeting = new Meeting(meetingName, eventDate,
                Optional.of(meetingStartTime), Optional.of(meetingEndTime));

        return new AddMeetingCommand(meeting);
    }
}
