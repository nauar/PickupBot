package info.nauar.irc.pickupBot.nick;

public interface NickService {

    void processNickChange(String oldNick, String newNick);
    boolean nickExists(String nick);
}
