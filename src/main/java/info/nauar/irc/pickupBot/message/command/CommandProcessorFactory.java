package info.nauar.irc.pickupBot.message.command;

public class CommandProcessorFactory {

    public static CommandProcessor getProcessor(String message) throws CommandException {
        String command = message.split(" ")[0];
        CommandProcessor commandProcessor = null;
        switch (command) {
            case "!add":
                commandProcessor = new HelpCommandProcessor();
                break;
            case "!help":
                commandProcessor = new HelpCommandProcessor();
                break;
            default:
                throw new CommandException("The command '" + command + "' is not available.");
        }
        return commandProcessor;
    }
}
