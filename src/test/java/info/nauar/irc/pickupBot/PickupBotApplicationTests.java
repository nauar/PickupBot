package info.nauar.irc.pickupBot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args={"--bot-name=johnny5-test", "--bridge-bot-name=bot-d2irc", "--channel=#bot.private", "--server=euroserv.fr.quakenet.org"})
class PickupBotApplicationTests {

	@Test
	void contextLoads() {
	}

}
