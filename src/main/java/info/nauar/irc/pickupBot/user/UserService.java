package info.nauar.irc.pickupBot.user;

public interface UserService {

    User createUser(String nick, String login, String hostname);
    User getUser(String nick);
    void deleteUser(String nick);
    void setNewNick(String oldNick, String newNick);
    boolean nickExists(String nick);
    boolean isConnectivityBot(String nick);
    boolean isOperator(String nick);
}
