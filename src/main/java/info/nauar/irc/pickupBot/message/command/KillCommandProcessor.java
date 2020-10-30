package info.nauar.irc.pickupBot.message.command;

import info.nauar.irc.pickupBot.PickupBot;
import org.jibble.pircbot.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class KillCommandProcessor implements CommandProcessor {

    private static final String[][] SENTENCES = {
            {
                "%s was filled of holes by %s!",
                    "%s was hit by %s's grenade!",
                    "%s stepped in %s's electroball!",
                    "%s was brought to a path of pain and death by %s!",
                    "%s was lasered to death by %s!",
                    "%s ate %s's rocket!",
                    "%s was murdered by %s!"
            },
            {
                "%s annihilated %s!",
                    "%s stopped %s's heartbeat!",
                    "%s threw %s to lava!",
                    "%s threw %s to acid!",
                    "%s planned %s's death with success!",
                    "%s cut %s's head",
                    "%s fragged %s!"
            },
            {
                "%s stepped in fire!",
                    "%s fell into the void!",
                    "%s killed himself by accident!",
                    "%s did not learn how to aim properly!",
                    "%s was killed by defender Anubis!",
                    "%s rocket jumped too much!",
                    "%s forgot to pick up enough health!"
            }
    };

    @Autowired
    private PickupBot pickupBot;

    @Override
    public void process(String channel, String sender, String login, String hostname, String message, boolean isPrivate) {
        String receiver = "";
        if (message.trim().contains(" ")) {
            receiver = message.trim().split(" ")[1];
        }
        pickupBot.sendMessage(channel, killSentenceGenerator(channel, sender, receiver));
    }

    private String killSentenceGenerator(String channel, String sender, String receiver) {
        int millis = (int) System.currentTimeMillis();
        User[] users = pickupBot.getUsers(channel);
        if (Arrays.stream(users).filter( user -> user.getNick().equals(receiver)).findFirst().isPresent()) {
            return SENTENCES[millis%2][millis%7];
        } else {
            return SENTENCES[2][millis%7];
        }
    }
}
