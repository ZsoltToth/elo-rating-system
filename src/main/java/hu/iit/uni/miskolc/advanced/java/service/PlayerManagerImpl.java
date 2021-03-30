package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.dao.PlayerRepository;
import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PlayerManagerImpl implements PlayerManager {

    private final PlayerRepository repository;

    @Override
    public void register(Player player) throws PlayerAlreadyExistsException {
        repository.create(player);
    }

    @Override
    public Player fetchByName(String name) {
        for (Player player : repository.readAll()) {
            if (player.getName()
                    .equals(name)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public Player updatePlayer(Player player) {
        return repository.update(player);
    }

    @Override
    public Collection<Player> fetchPlayers() {
        return repository.readAll();
    }
}
