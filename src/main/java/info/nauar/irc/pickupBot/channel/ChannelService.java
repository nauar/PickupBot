package info.nauar.irc.pickupBot.channel;

public interface ChannelService {

    Channel createChannel(String name, String motd);
    boolean channelExists(String name);
    void setGameStatus(String gameStatus);
    void broadcast(String message);
}
