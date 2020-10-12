package info.nauar.irc.pickupBot.channel;

import info.nauar.irc.pickupBot.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

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
}
