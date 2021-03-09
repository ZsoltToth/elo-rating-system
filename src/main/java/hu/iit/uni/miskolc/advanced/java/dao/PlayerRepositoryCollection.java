package hu.iit.uni.miskolc.advanced.java.dao;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PlayerRepositoryCollection implements PlayerRepository {

    private Set<Player> players;

    public PlayerRepositoryCollection() {
        this.players = new HashSet<>();
    }

    @Override
    public void add(Player player) throws PlayerAlreadyExistsException {
        if (players.contains(player)) {
            throw new PlayerAlreadyExistsException();
        }
        players.add(player);
    }

    @Override
    public Collection<Player> readAll() {
        return new HashSet<>(players);
    }

    @Override
    public Player save(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
        players.add(player);
        return player;
    }
}
