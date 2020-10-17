package info.nauar.irc.pickupBot.message;

import info.nauar.irc.pickupBot.message.command.CommandException;

public interface MessageService {

    void processMessage(String channel, String sender, String login, String hostname, String message) throws CommandException;
}
