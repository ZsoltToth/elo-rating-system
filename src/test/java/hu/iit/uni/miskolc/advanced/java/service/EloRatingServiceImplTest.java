package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EloRatingServiceImplTest {

    @Mock
    private PlayerManager playerManager;

    @InjectMocks
    private EloRatingServiceImpl eloRatingService;

    @Test
    void happyPath() {
        // given
        Player alice = new Player("alice", 1000);
        Player bob = new Player("bob", 1000);
        final int aliceWin = 1;
        final int bobWin = 0;
        doReturn(alice).doReturn(bob).when(playerManager).updatePlayer(any());
        // when
        eloRatingService.updatePlayerRankings(alice, bob, aliceWin, bobWin);
        // then
        verify(playerManager, times(2)).updatePlayer(any());
    }
}