package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import org.jibble.pircbot.Colors;
import org.springframework.beans.factory.annotation.Autowired;

public class HelpCommandProcessor implements CommandProcessor {

    @Autowired
    private PickupBot pickupBot;

    private static final String COMMAND = "!help";
    private static final String GENERIC_MESSAGE = Colors.BOLD + "!help <command>" + Colors.NORMAL
            + ": Gives help on a particular command. " + Colors.BOLD + "Available commands are: " + Colors.NORMAL +
            " abort*, add, commands, help, kill, motd*, pickups, promote, pull*, remove, start*, subscribe, unsubscribe, who";

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean isPrivate) {

        if (message.trim().equals(COMMAND)) {
            processGenericHelp(sender);
        } else {
            String command = getCommandParameter(message);
            switch (command) {
                case "abort":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!abort [game [game ...]]" + Colors.NORMAL + Colors.LIGHT_GRAY + "(not from PM, admin command)" + Colors.NORMAL + ": Aborts a game.");
                    break;
                case "add":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!add [game [game ...]]" + Colors.NORMAL + Colors.LIGHT_GRAY + "(not from PM)" + Colors.NORMAL + ": Signs you up for one or more games.");
                    break;
                case "commands":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!commands" + Colors.NORMAL + ": Lists all commands.");
                    break;
                case "help":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!help [command]" + Colors.NORMAL + ": If a command is given, shows a description of it. Lists all the possible commands otherwise.");
                    break;
                case "kill":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!kill <nick>" + Colors.NORMAL + Colors.LIGHT_GRAY + "(not from PM)" + Colors.NORMAL + ": Make the bot mention the user with nickname <nick>.");
                    break;
                case "lastgame":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!lastgame" + Colors.NORMAL + ": Shows when the last game started and which players were in it.");
                    break;
                case "lastgames":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!lastgames" + Colors.NORMAL + ": Add players to matchmaking playerlist. This command will add a +v flag to the given users in IRC.");
                    break;
                case "motd":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!motd [...|--]" + Colors.NORMAL + Colors.LIGHT_GRAY + "(admin command)" + Colors.NORMAL + ": Sets the message of the day in the channel topic. !motd -- will reset it to default.");
                    break;
                case "note":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!note <to> <message>" + Colors.NORMAL + ": Add a message for the given nick. The user will be notified when joining the channel.");
                    break;
                case "pickups":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!pickups [search]" + Colors.NORMAL + ": Lists games available for pickups.");
                    break;
                case "promote":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!promote <game>" + Colors.NORMAL + ": Shows a notice encouraging players to sign up for the specified game.");
                    break;
                case "pull":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!pull <player> [game [game ..]]" + Colors.NORMAL + Colors.LIGHT_GRAY + "(not from PM, admin command)" + Colors.NORMAL + ": Removes someone else from one or more games.");
                    break;
                case "remove":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!remove [game [game ..]]" + Colors.NORMAL + Colors.LIGHT_GRAY + "(not from PM)"+ Colors.NORMAL + ": Removes you from one or more games.");
                    break;
                case "renew":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!renew" + Colors.NORMAL + Colors.LIGHT_GRAY + "(not from PM)" + Colors.NORMAL + ": Renew your previous sign ups to all games (they die in 1h).");
                    break;
                case "start":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!start <game>" + Colors.NORMAL + Colors.LIGHT_GRAY + "(not from PM, admin command)" + Colors.NORMAL + ": Forces the game to start, even if not enough players signed up.");
                    break;
                case "subscribe":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!subscribe [game [game ..]]" + Colors.NORMAL + ": Subscribe for highlights on a specific gametype, e.g. ctf or dm.");
                    break;
                case "top10":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!top10 [game [game ..]]" + Colors.NORMAL + ": Shows who participated most in said game(s).");
                    break;
                case "unsubscribe":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!unsubscribe [game [game ..]]" + Colors.NORMAL + ": Unsubscribe for highlights on a specific gametype, e.g. ctf or dm.");
                    break;
                case "who":
                    pickupBot.sendNotice(sender, Colors.BOLD + "!who [game [game ..]]" + Colors.NORMAL + ": Shows who has signed up.");
                    break;
                default:
                    pickupBot.sendNotice(sender, "The command '" + command + "' is not available.");
            }
        }
    }

    private void processGenericHelp(String nick) {
        pickupBot.sendNotice(nick, GENERIC_MESSAGE);
    }

    private String getCommandParameter(String message) {
        return message.split(" ",2)[1];
    }
}
