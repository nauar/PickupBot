package info.nauar.irc.pickupBot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(String nick, String login, String hostname) {
        User user;
        if (!nickExists(nick)) {
            user = new User();
            user.setNick(nick);
            user.setLogin(login);
            user.setHostname(hostname);
            userRepository.save(user);
        } else {
            user = userRepository.findById(nick).orElseThrow();
        }
        return user;
    }

    @Override
    public User getUser(String nick) {
        return userRepository.findById(nick).orElseThrow();
    }

    @Override
    public void setNewNick(String oldNick, String newNick) {
        User user = userRepository.findById(oldNick).orElseThrow();
        user.setNick(newNick);
        userRepository.save(user);
    }

    @Override
    public boolean nickExists(String nick) {
        return userRepository.findById(nick).isPresent();
    }

    @Override
    public void deleteUser(String nick) {
        userRepository.delete(userRepository.findById(nick).orElseThrow());
    }

    @Override
    public boolean isConnectivityBot(String nick) {
        return userRepository.findById(nick).orElseThrow().isConnectivityBot();
    }

    @Override
    public boolean isOperator(String nick) {
        return userRepository.findById(nick).orElseThrow().isOperator();
    }


}
