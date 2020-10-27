package info.nauar.irc.pickupBot.game;

import info.nauar.irc.pickupBot.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Data
@Entity
@NoArgsConstructor
public class GameType {

    @Id
    private String name;
    private Integer totalPlayers;
    @ManyToMany(mappedBy = "signedUpGameTypes")
    private Set<User> signedUsers = new CopyOnWriteArraySet<>();
    @ManyToMany(mappedBy = "subscribedGameTypes")
    private Set<User> subscriptors = new CopyOnWriteArraySet<>();

}
