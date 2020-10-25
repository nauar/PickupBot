package info.nauar.irc.pickupBot.message;

import info.nauar.irc.pickupBot.message.command.CommandException;

public interface MessageService {

    void processMessage(Message message) throws CommandException;

}
