package info.nauar.irc.pickupBot.channel;

public interface ChannelService {

    Channel createChannel(String name);
    boolean channelExists(String name);
    void setGameStatus(String gameStatus);
}
