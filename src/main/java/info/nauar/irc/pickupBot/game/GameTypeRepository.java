package info.nauar.irc.pickupBot.game;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameTypeRepository extends CrudRepository<GameType, Long> {
    Optional<GameType> findByName(String name);
}
