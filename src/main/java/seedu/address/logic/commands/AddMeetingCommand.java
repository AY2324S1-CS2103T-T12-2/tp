package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Meeting;
import seedu.address.model.person.Name;


/**
 * Adds a meeting.
 * @author Yuheng
 */
public class AddMeetingCommand extends Command {

    public static final String COMMAND_WORD = "add_meeting";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the address book. "
            + "Parameters: "
            + PREFIX_MEETING_NAME + "MEETING_NAME "
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_START_TIME + "START_TIME]"
            + "[" + PREFIX_END_TIME + "END_TIME]\n"
            + "[" + PREFIX_NAME + "NAME]..."
            + "[" + PREFIX_GROUP + "GROUP]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MEETING_NAME + "CS2103T Meeting "
            + PREFIX_DATE + "2020-10-10 "
            + PREFIX_START_TIME + "1000 "
            + PREFIX_END_TIME + "1200 "
            + PREFIX_NAME + "Alice "
            + PREFIX_NAME + "Bob"
            + PREFIX_GROUP + "Team1";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireAllNonNull(meeting);
        this.toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        CommandUtil.verifyEventTimes(this.toAdd);

        Set<Name> invalidNames = model.findInvalidNames(this.toAdd.getNames());

        if (!invalidNames.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_PERSON,
                    listInvalidNames(invalidNames)));
        }
        model.addEvent(this.toAdd); //else, all the names exist
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatEvent(toAdd)));
    }

    private String listInvalidNames(Set<Name> invalidNames) {
        StringBuilder builder = new StringBuilder();
        for (Name name : invalidNames) {
            builder.append(name.toString());
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length()); //removes the last comma
        return builder.toString();
    }
}
