package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;

import java.util.Collection;

public interface PlayerManager {

    void register(Player player) throws PlayerAlreadyExistsException;

    Player fetchByName(String name);
    Collection<Player> fetchPlayers();
}
