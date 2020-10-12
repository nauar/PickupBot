package info.nauar.irc.pickupBot.game;

import info.nauar.irc.pickupBot.message.command.CommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameTypeServiceImpl implements GameTypeService {

    @Autowired
    private GameTypeRepository gameTypeRepository;

    @Override
    public GameType createGameType(String name, Integer totalPlayers) throws CommandException {
        GameType toCreate;
        Optional<GameType> exists = gameTypeRepository.findByName(name);
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
        Optional<GameType> gameType = gameTypeRepository.findByName(name);
        if (gameType.isPresent())
            gameTypeRepository.delete(gameType.get());
    }

    @Override
    public void add(List<String> names) {
        for (GameType gameType : gameTypeRepository.findAll()) {
            if (names.isEmpty() || names.contains(gameType(gameType.getName())))
                gameType.getSignedUsers().add()
        }


    }
}
