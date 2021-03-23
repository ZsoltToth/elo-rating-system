package hu.iit.uni.miskolc.advanced.java.dao;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;

import java.util.Collection;

public interface PlayerRepository {

    void create(Player player) throws PlayerAlreadyExistsException;

    Collection<Player> readAll();

    Player update(Player player);
}
