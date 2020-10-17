package info.nauar.irc.pickupBot.message.command;

public interface CommandProcessor {

    void process(String channel, String sender, String login, String hostname, String message);
}
