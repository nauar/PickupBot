package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import org.springframework.beans.factory.annotation.Autowired;

public class KillCommandProcessor implements CommandProcessor {

    @Autowired
    private PickupBot pickupBot;

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean isPrivate) {

    }
}
