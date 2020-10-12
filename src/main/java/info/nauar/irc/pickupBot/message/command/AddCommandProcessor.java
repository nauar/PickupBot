package info.nauar.irc.pickupBot.message.command;

public class AddCommandProcessor implements CommandProcessor {

    private static final String COMMAND = "!add";

    @Override
    public void process(String nick, String message) {
        if (message.trim().equals(COMMAND)) {
            processGenericAdd(nick);
        } else {

        }
    }

    private void processGenericAdd(String nick) {

    }
}
