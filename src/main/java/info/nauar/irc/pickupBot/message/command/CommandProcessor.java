package info.nauar.irc.pickupBot.message.command;

public interface CommandProcessor {

    void process(String nick, String message);
}
