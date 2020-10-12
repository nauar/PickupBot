package info.nauar.irc.pickupBot.channel;

import info.nauar.irc.pickupBot.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Data
@Entity
@NoArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "channels")
    private Set<User> users = new CopyOnWriteArraySet<>();
}
