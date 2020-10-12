package info.nauar.irc.pickupBot.user;

public interface UserService {

    User createUser(String nick, String login, String hostname);
    void deleteUser(String nick);
    void setNewNick(String oldNick, String newNick);
    boolean nickExists(String nick);
    boolean isConnectivityBot(String nick);
    boolean isOperator(String nick);
}
