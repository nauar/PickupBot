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
    private UserService userService;

    @Override
    public void processMessage(String channel, String sender, String login, String hostname, String message) throws CommandException {

        String nick = sender;

        if (userService.isConnectivityBot(sender)) {
            message = adaptConnectivityBotMessage(message);
            nick = getConnectivityBotMessageSender(message) + '_' + sender;
        }

        if (message.startsWith("!"))
            processCommand(channel, sender, login, hostname, message);

    }

    private String adaptConnectivityBotMessage(String message) {
        return message.substring(message.indexOf('>') + 2);
    }

    private String getConnectivityBotMessageSender(String message) {
        return message.substring(message.indexOf('<')+1,message.indexOf('>'));
    }

    private void processCommand(String channel, String sender, String login, String hostname, String message) throws CommandException {
        CommandProcessorFactory.getProcessor(message).process(channel, sender, login, hostname, message);
    }

}
