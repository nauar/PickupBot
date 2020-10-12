package info.nauar.irc.pickupBot.event;

import info.nauar.irc.pickupBot.user.User;
import info.nauar.irc.pickupBot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    UserService userService;

    @Override
    public void processJoin(String channel, String sender, String login, String hostname) {
        User user = userService.createUser(sender, login, hostname);
    }

    @Override
    public void processPart(String channel, String sender, String login, String hostname) {
        userService.deleteUser(sender);
    }

    @Override
    public void processKick(String channel, String kickerNick, String kickerLogin, String kickerHostname, String recipientNick, String reason) {
        userService.deleteUser(recipientNick);
    }

    @Override
    public void processQuit(String sourceNick, String sourceLogin, String sourceHostname, String reason) {
        userService.deleteUser(sourceNick);
    }
}
