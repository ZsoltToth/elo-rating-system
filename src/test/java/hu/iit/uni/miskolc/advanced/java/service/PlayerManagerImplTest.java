package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.dao.PlayerRepository;
import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
class PlayerManagerImplTest {

    @Mock
    PlayerRepository repository;

    @InjectMocks
    PlayerManagerImpl playerManager;

    final Player alice = new Player("alice", 1000);

    @Test
    void register() throws PlayerAlreadyExistsException {
        // given
        doNothing().when(repository).create(alice);
        // when
        playerManager.register(alice);
        // then
    }

    @Test
    @DisplayName("Register throws exception if player already exists")
    void registerShouldTrhowExceptionIfPlayerAlreadyExists() throws PlayerAlreadyExistsException {
        // given
        doThrow(PlayerAlreadyExistsException.class).when(repository).create(alice);
        // when
        assertThatThrownBy(() -> {
            playerManager.register(alice);
        }).isInstanceOf(PlayerAlreadyExistsException.class);
        // then
    }


    @Test
    void fetchByName() {
        // given
        final String nameOfAlice = "alice";
        doReturn(List.of(alice)).when(repository).readAll();
        // when
        final Player actual = playerManager.fetchByName(nameOfAlice);
        // then
        assertThat(actual).isEqualTo(alice);
    }

    @Test
    void fetchByNameOfUnknownPlayer() {
        // given
        final String unknownName = "unknown_player_name";
        doReturn(List.of(alice)).when(repository).readAll();
        // when
        final Player actual = playerManager.fetchByName(unknownName);
        // then
        assertThat(actual).isNull();
    }

    @Test
    void fetchPlayers() {
    }
}