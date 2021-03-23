package hu.iit.uni.miskolc.advanced.java.dao;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerRepositoryCollectionTest {

    private PlayerRepositoryCollection playerRepository;

    @BeforeEach
    void setUp(){
        this.playerRepository = new PlayerRepositoryCollection();
    }

    @Test
    void create() throws PlayerAlreadyExistsException {
        //given
        Player alice = new Player("alice", 1000);
        //when
        playerRepository.create(alice);
        //then
        assertThat(playerRepository.readAll()).contains(alice);
    }

    @Test
    @DisplayName("Insert Same player twice should throw exception")
    void shouldTrhowExceptionIsPlayerCreatesExistingPlayer() throws PlayerAlreadyExistsException {
        //given
        Player alice = new Player("alice", 1000);
        Player anotherSamePlayer = new Player("alice", 1000);
        //when
        assertThatThrownBy(()->{
            playerRepository.create(alice);
            playerRepository.create(anotherSamePlayer);
        }).isInstanceOf(PlayerAlreadyExistsException.class);
        //then
    }

    @Test
    @DisplayName("Read all players from empty repository")
    void readAll() {
        //given
        //when
        Collection<Player> actual = playerRepository.readAll();
        //then
        assertThat(actual).isEmpty();
    }

    @Test
    void update() {
    }
}