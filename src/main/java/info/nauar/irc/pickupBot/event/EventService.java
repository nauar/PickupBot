package info.nauar.irc.pickupBot.event;

public interface EventService {

    void processJoin(String channel, String sender, String login, String hostname);
    void processPart(String channel, String sender, String login, String hostname);
    void processKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason);
    void processQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason);
}
