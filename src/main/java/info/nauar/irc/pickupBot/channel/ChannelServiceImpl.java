package info.nauar.irc.pickupBot.channel;

import info.nauar.irc.pickupBot.PickupBot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private PickupBot pickupBot;

    @Override
    public boolean channelExists(String name) {
        return false;
    }

    @Override
    public Channel createChannel(String name) {
        Channel channel;
        if (!channelExists(name)) {
            channel = new Channel();
            channel.setName(name);
            channelRepository.save(channel);
        } else {
            channel = channelRepository.findByName(name).orElseThrow();
        }
        return channel;
    }

    @Override
    public void setGameStatus(String gameStatus) {
        Channel.setGameStatus(gameStatus);
        Arrays.stream(pickupBot.getChannels()).parallel().forEach(channelName -> {
            pickupBot.setTopic(channelName, channelRepository.findByName(channelName).orElseThrow().getFullMotd());
        });
    }
}
