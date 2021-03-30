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

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        PlayerManager playerManager = context.getBean(PlayerManager.class);
        EloRatingService service = context.getBean(EloRatingService.class);

        playerManager.register(new Player("Alice", 1000));
        playerManager.register(new Player("Bob", 1000));
        playerManager.register(new Player("Charlie", 1000));

        playerManager.fetchPlayers()
                .forEach(System.out::println);

        service.updatePlayerRankings(
                playerManager.fetchByName("Alice"),
                playerManager.fetchByName("Bob"),
                1,0
        );
        playerManager.fetchPlayers()
                .forEach(System.out::println);
    }
}
