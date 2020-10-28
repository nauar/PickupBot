package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import info.nauar.irc.pickupBot.game.GameTypeService;
import info.nauar.irc.pickupBot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class AbortCommandProcessor implements CommandProcessor {

    private static final String PRIVATE_RESPONSE = "Abort command cannot be used in private.";
    private static final String NO_ADMIN_RESPONSE = "Abort command requires you to be channel operator.";
    private static final String GAME_NOT_PRESENT = "There is not such game: ";

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
        } else if (!userService.isOperator(sender)) {
            pickupBot.sendNotice(sender, NO_ADMIN_RESPONSE);
        } else {
            gameTypeService.abort(Arrays.asList(message.split(" ").clone()));
        }

    }

}
