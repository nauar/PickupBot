package info.nauar.irc.pickupBot.channel;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChannelRepository extends CrudRepository<Channel, Long> {

    Optional<Channel> findByName(String name);

}
