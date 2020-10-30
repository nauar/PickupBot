package info.nauar.irc.pickupBot.configuration;

import info.nauar.irc.pickupBot.PickupBot;
import info.nauar.irc.pickupBot.channel.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.NoSuchElementException;

@Configuration
public class AppConfig {

    private static final String ARG_BRIDGEBOTNAME = "bridge-bot-name";
    private static final String ARG_BOTNAME = "bot-name";
    private static final String ARG_CHANNEL = "channel";
    private static final String ARG_SERVER = "server";
    
    @Autowired
    private ApplicationArguments args;

    @Bean
    public PickupBot pickupBot() throws Exception {
        PickupBot pickupBot;

        if (args.containsOption(ARG_BOTNAME)) {
            List<String> values = args.getOptionValues(ARG_BOTNAME);
            if (!values.isEmpty()) {
                pickupBot = new PickupBot(values.get(0));
            } else {
                throw new NoSuchElementException("Parameter --" + ARG_BOTNAME + " must have one value.");
            }
        } else {
            throw new NoSuchElementException("Missing parameter in command line: --" + ARG_BOTNAME);
        }
        pickupBot.setVerbose(true);
        if (args.containsOption(ARG_BRIDGEBOTNAME)) {
            List<String> values = args.getOptionValues(ARG_BRIDGEBOTNAME);
            if (!values.isEmpty()) {
                pickupBot.setBridgeBotName(values.get(0));
            } else {
                throw new NoSuchElementException("Parameter --" + ARG_BRIDGEBOTNAME + " must have one value.");
            }
        } else {
            throw new NoSuchElementException("Missing parameter in command line: --" + ARG_BRIDGEBOTNAME);
        }
        if (args.containsOption(ARG_SERVER)) {
            List<String> values = args.getOptionValues(ARG_SERVER);
            if (!values.isEmpty()) {
                pickupBot.connect(values.get(0));
            } else {
                throw new NoSuchElementException("Parameter --" + ARG_SERVER + " must have one value.");
            }
        } else {
            throw new NoSuchElementException("Missing parameter in command line: --" + ARG_SERVER);
        }
        if (args.containsOption(ARG_CHANNEL)) {
            List<String> values = args.getOptionValues(ARG_CHANNEL);
            if (!values.isEmpty()) {
                for (String value : values) {
                    pickupBot.joinChannel(value);
                }
            } else {
                throw new NoSuchElementException("Parameter --" + ARG_CHANNEL + " must have at least one value.");
            }
        } else {
            throw new NoSuchElementException("Missing parameter in command line: --" + ARG_CHANNEL);
        }
        return pickupBot;
    }

}
