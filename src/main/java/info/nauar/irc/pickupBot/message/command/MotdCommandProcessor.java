package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import info.nauar.irc.pickupBot.channel.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;

public class MotdCommandProcessor implements CommandProcessor {

    private static final String ERROR_PROCESSING = "Error processing your !motd request.";

    @Autowired
    private ChannelService channelService;

    @Autowired
    private PickupBot pickupBot;

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean isPrivate) {
        try {
            String topic = message.split(" ",2)[1];
            channelService.setMotd(channel, topic);
        } catch (Exception e) {
            pickupBot.sendNotice(sender, ERROR_PROCESSING);
        }
    }
}
