package hu.iit.uni.miskolc.advanced.java.dao;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;

import java.util.Collection;

public interface PlayerRepository {

    void add(Player player) throws PlayerAlreadyExistsException;

    Collection<Player> readAll();

    Player save(Player player);
}
