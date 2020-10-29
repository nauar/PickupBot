package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import org.jibble.pircbot.Colors;
import org.springframework.beans.factory.annotation.Autowired;

public class CommandsCommandProcessor implements CommandProcessor {

    private static final String COMMANDS = Colors.BOLD + "Available commands are: " + Colors.NORMAL +
            " abort*, add, commands, help, kill, lastgame, lastgames, motd*, note, pickups, promote, pull*, remove, renew, start*, subscribe, top10, unsubscribe, who";


    @Autowired
    private PickupBot pickupBot;

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean isPrivate) {
        pickupBot.sendNotice(sender, COMMANDS);
    }
}
