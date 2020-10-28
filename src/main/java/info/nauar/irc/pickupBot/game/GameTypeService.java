package info.nauar.irc.pickupBot.game;

import info.nauar.irc.pickupBot.message.command.CommandException;

import java.util.List;

public interface GameTypeService {

    GameType createGameType(String name, Integer totalPlayers) throws CommandException;
    void deleteGameType(String name) throws CommandException;
    void abort(List<String> names);
    void add(String nick, List<String> names);
    GameType getGameType(String name);

}
