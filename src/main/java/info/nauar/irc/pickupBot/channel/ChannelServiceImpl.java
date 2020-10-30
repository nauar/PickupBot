package info.nauar.irc.pickupBot.channel;

import info.nauar.irc.pickupBot.PickupBot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class ChannelServiceImpl implements ChannelService {

    private static final String CHANNEL_ALREADY_EXISTS = "Channel already exists.";

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private PickupBot pickupBot;

    @Override
    public boolean channelExists(String name) {
        return channelRepository.existsById(name);
    }

    @Override
    public Channel createChannel(String name, String motd) {
        Channel channel;
        if (!channelExists(name)) {
            channel = new Channel();
            channel.setName(name);
            channel.setMotd(motd);
            channelRepository.save(channel);
        } else {
            throw new RuntimeException(CHANNEL_ALREADY_EXISTS);
        }
        return channel;
    }

    @Override
    public void setGameStatus(String gameStatus) {
        Channel.setGameStatus(gameStatus);
        Arrays.stream(pickupBot.getChannels()).parallel().forEach(channelName -> {
            pickupBot.setTopic(channelName, channelRepository.findById(channelName).orElseThrow().getFullMotd());
        });
    }

    @Override
    public void broadcast(String message) {
        channelRepository.findAll().forEach(channel -> {
            pickupBot.sendMessage(channel.getName(), message);
        });
    }
}
