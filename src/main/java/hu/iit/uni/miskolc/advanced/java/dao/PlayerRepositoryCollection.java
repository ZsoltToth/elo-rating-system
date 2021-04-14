package hu.iit.uni.miskolc.advanced.java.dao;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class PlayerRepositoryCollection implements PlayerRepository {

    private Set<Player> players;

    public PlayerRepositoryCollection() {
        this.players = new HashSet<>();
    }

    @Override
    public void create(Player player) throws PlayerAlreadyExistsException {
        if (players.contains(player)) {
            throw new PlayerAlreadyExistsException(
                    String.format("Player with name (%s) already exists!", player.getName())
            );
        }
        players.add(player);
    }

    @Override
    public Collection<Player> readAll() {
        return new HashSet<>(players);
    }

    @Override
    public Player update(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
        players.add(player);
        return player;
    }
}
