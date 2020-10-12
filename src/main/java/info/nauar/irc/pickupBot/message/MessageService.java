package info.nauar.irc.pickupBot.message;

public interface MessageService {

    void processMessage(String channel, String sender, String login, String hostname, String message);
}
