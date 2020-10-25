package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import org.springframework.beans.factory.annotation.Autowired;

public class AbortCommandProcessor implements CommandProcessor {

    @Autowired
    private PickupBot pickupBot;

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean isPrivate) {
        if (isPrivate) {
            // TODO: check answer the current pickup bot does
        }

        String[] games = message.split(" ");

        for (int i = 1; i < games.length; ++i) {
            abortGame(games[i]);
        }

    }

    private void abortGame(String game) {

    }
}
