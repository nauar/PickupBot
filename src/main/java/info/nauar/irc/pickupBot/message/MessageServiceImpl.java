package info.nauar.irc.pickupBot.message;

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
    public void processMessage(Message message) throws CommandException {
        String nick = message.getSender();
        String messageText = message.getMessage();

        if (userService.isConnectivityBot(nick)) {
            messageText = adaptConnectivityBotMessage(message.getMessage());
            nick = getConnectivityBotMessageSender(messageText) + '_' + nick;
        }

        if (messageText.startsWith("!"))
            processCommand(message.getChannel(), message.getSender(), message.getLogin(), message.getMessage(), messageText, message.isPrivate());
    }

    private String adaptConnectivityBotMessage(String message) {
        return message.substring(message.indexOf('>') + 2);
    }

    private String getConnectivityBotMessageSender(String message) {
        return message.substring(message.indexOf('<')+1,message.indexOf('>'));
    }

    private void processCommand(String channel, String sender, String login, String hostname, String message, boolean priv) throws CommandException {
        CommandProcessorFactory.getProcessor(message).process(channel, sender, login, hostname, message, priv);
    }

}
