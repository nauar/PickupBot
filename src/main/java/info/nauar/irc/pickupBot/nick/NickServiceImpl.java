package info.nauar.irc.pickupBot.nick;

import info.nauar.irc.pickupBot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NickServiceImpl implements NickService {

    @Autowired
    private UserService userService;

    @Override
    public void processNickChange(String oldNick, String newNick) {
        userService.setNewNick(oldNick, newNick);
    }

    @Override
    public boolean nickExists(String nick) {
        return userService.nickExists(nick);
    }
}
