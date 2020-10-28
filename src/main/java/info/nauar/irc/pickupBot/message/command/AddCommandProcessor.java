package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import info.nauar.irc.pickupBot.game.GameTypeService;
import info.nauar.irc.pickupBot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class AddCommandProcessor implements CommandProcessor {

    private static final String PRIVATE_RESPONSE = "Add command cannot be used in private.";


    @Autowired
    private PickupBot pickupBot;

    @Autowired
    private UserService userService;

    @Autowired
    private GameTypeService gameTypeService;

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean isPrivate) {

        if (isPrivate) {
            pickupBot.sendNotice(sender, PRIVATE_RESPONSE);
        } else {
            gameTypeService.add(sender, Arrays.asList(message.split(" ").clone()));
        }
    }
}
