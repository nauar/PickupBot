package info.nauar.irc.pickupBot.message.command;

public class AddCommandProcessor implements CommandProcessor {

    private static final String COMMAND = "!add";

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean priv) {
        if (message.trim().equals(COMMAND)) {
            processGenericAdd(sender);
        } else {

        }
    }

    private void processGenericAdd(String nick) {

    }
}
