package info.nauar.irc.pickupBot.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private static final String PRIVATE_MESSAGE_CHANNEL = "%%%%";
    private String channel = PRIVATE_MESSAGE_CHANNEL;
    private String sender;
    private String login;
    private String hostname;
    private String message;

    public boolean isPrivate() {
        return PRIVATE_MESSAGE_CHANNEL.equals(channel);
    }

}
