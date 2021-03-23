package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EloRatingServiceImplTest {

    @Mock
    private PlayerManager playerManager;

    @Mock
    private EloRatingCalculator eloRatingCalculator;

    @InjectMocks
    private EloRatingServiceImpl eloRatingService;

    @Test
    void happyPath() {
        // given
        final int aliceOldScore = 1000;
        final int aliceNewScore = 1008;
        final int bobOldScore = 1000;
        final int bobNewScore = 992;
        Player alice = new Player("alice", aliceOldScore);
        Player bob = new Player("bob", bobOldScore);
        final int aliceWin = 1;
        final int bobWin = 0;
        doReturn(alice).doReturn(bob).when(playerManager).updatePlayer(any());
        doReturn(
                EloRatingCalculator.CalculatedScores.builder()
                        .user1(aliceNewScore)
                        .user2(bobNewScore)
                        .build())
                .when(eloRatingCalculator)
                .calculateScores(any());
        // when
        eloRatingService.updatePlayerRankings(alice, bob, aliceWin, bobWin);
        // then
        verify(playerManager, times(2)).updatePlayer(any());
    }
}