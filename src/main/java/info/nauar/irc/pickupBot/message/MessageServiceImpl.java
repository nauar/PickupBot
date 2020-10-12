package info.nauar.irc.pickupBot.message;

import info.nauar.irc.pickupBot.PickupBot;
import info.nauar.irc.pickupBot.message.command.CommandException;
import info.nauar.irc.pickupBot.message.command.CommandProcessorFactory;
import info.nauar.irc.pickupBot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private PickupBot pickupBot;

    @Autowired
    private UserService userService;

    @Override
    public void processMessage(String channel, String sender, String login, String hostname, String message) {

        String nick = sender;

        if (userService.isConnectivityBot(sender)) {
            message = adaptConnectivityBotMessage(message);
            nick = getConnectivityBotMessageSender(message) + '_' + sender;
        }

        if (message.startsWith("!"))
            processCommand(nick, message);

    }

    private String adaptConnectivityBotMessage(String message) {
        return message.substring(message.indexOf('>') + 2);
    }

    private String getConnectivityBotMessageSender(String message) {
        return message.substring(message.indexOf('<')+1,message.indexOf('>'));
    }

    private void processCommand(String nick, String message) {
        String[] command = message.substring(1).split(" ");
        try {
            CommandProcessorFactory.getProcessor(message).process(nick, message);
        } catch (CommandException commandExcepton) {
            pickupBot.sendNotice(nick, commandExcepton.getMessage());
        }
    }

}
