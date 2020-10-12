package info.nauar.irc.pickupBot.user;

import info.nauar.irc.pickupBot.channel.Channel;
import info.nauar.irc.pickupBot.game.GameType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Data
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String nick;
	private String login;
	private String hostname;
	private boolean isConnectivityBot;
	private boolean isOperator;
	@ManyToMany
	@JoinTable(
			name = "user_channel",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "channel_id")
	)
	private Set<Channel> channels = new CopyOnWriteArraySet<>();

	@ManyToMany
	@JoinTable(
			name = "user_signup",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "gametype_id")
	)
	private Set<GameType> signedUpGameTypes = new CopyOnWriteArraySet<>();

	@ManyToMany
	@JoinTable(
			name = "user_subscription",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "gametype_id")
	)
	private Set<GameType> subscribedGameTypes = new CopyOnWriteArraySet<>();


}
