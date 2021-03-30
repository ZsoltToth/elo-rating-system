package hu.iit.uni.miskolc.advanced.java;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.EloRatingService;
import hu.iit.uni.miskolc.advanced.java.service.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class App implements CommandLineRunner {

    private static final String ALICE = "Alice";

    private static final String BOB = "Bob";

    private static final String CHARLIE = "Charlie";

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        PlayerManager playerManager = context.getBean(PlayerManager.class);

        playerManager.register(new Player(ALICE, 1000));
        playerManager.register(new Player(BOB, 1000));
        playerManager.register(new Player(CHARLIE, 1000));

        playerManager.fetchPlayers()
                .forEach(System.out::println);

        EloRatingService service = context.getBean(EloRatingService.class);
        service.updatePlayerRankings(
                playerManager.fetchByName(ALICE),
                playerManager.fetchByName(BOB)
        );
        playerManager.fetchPlayers()
                .forEach(System.out::println);
    }
}
