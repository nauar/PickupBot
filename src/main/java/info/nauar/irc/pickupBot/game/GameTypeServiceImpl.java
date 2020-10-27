package info.nauar.irc.pickupBot.game;

import info.nauar.irc.pickupBot.PickupBot;
import info.nauar.irc.pickupBot.channel.ChannelService;
import info.nauar.irc.pickupBot.message.command.CommandException;
import info.nauar.irc.pickupBot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameTypeServiceImpl implements GameTypeService {

    private static final String GAME_ABORTED = "GAME ABORTED: ";

    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Autowired
    private PickupBot pickupBot;

    @Autowired
    private ChannelService channelService;

    @Override
    public GameType createGameType(String name, Integer totalPlayers) throws CommandException {
        GameType toCreate;
        Optional<GameType> exists = gameTypeRepository.findById(name);
        if (exists.isEmpty()) {
            toCreate = new GameType();
            toCreate.setName(name);
            toCreate.setTotalPlayers(totalPlayers);
        } else {
            throw new CommandException("A gametype with name '" + name + "' already exists.");
        }
        return toCreate;
    }

    @Override
    public void deleteGameType(String name) throws CommandException {
        Optional<GameType> gameType = gameTypeRepository.findById(name);
        if (gameType.isPresent())
            gameTypeRepository.delete(gameType.get());
    }

    @Override
    public void abort(List<String> names) {
        gameTypeRepository.findAll().forEach(gameType -> {
            if (names.isEmpty() || (names.contains(gameType.getName())) && !gameType.getSignedUsers().isEmpty()) {
                gameType.getSignedUsers().clear();
                gameTypeRepository.save(gameType);
                gameType.getSubscriptors().forEach((user -> {
                    pickupBot.sendNotice(user.getNick(), GAME_ABORTED + gameType.getName());
                }));
            }
        });
        updateGameStatus();
    }

    private void updateGameStatus() {
        String gamesStatus = "";
        for (GameType gameType : gameTypeRepository.findAll()) {
            if (gameType.getSignedUsers().size() > 0) {
                gamesStatus += " " + gameType.getName() + "[" + gameType.getSignedUsers().size() + "/" + gameType.getTotalPlayers() + "]";
            }
        }
        channelService.setGameStatus(gamesStatus);
    }

    @Override
    public GameType getGameType(String name) {
        return gameTypeRepository.findById(name).orElseThrow();
    }
}
