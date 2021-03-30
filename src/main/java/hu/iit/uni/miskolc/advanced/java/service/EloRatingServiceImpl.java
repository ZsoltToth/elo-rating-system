package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EloRatingServiceImpl implements EloRatingService {

    private final PlayerManager playerManager;

    private final EloRatingCalculator eloRatingCalculator;

    @Override
    public void updatePlayerRankings(Player winner, Player loser) {
        EloRatingCalculator.CalculatedScores calculatedScores = eloRatingCalculator.calculateScores(
                new EloRatingCalculator.GameResult(
                        winner.getScore(),
                        loser.getScore(),
                        1,
                        0
                ));
        winner.setScore(calculatedScores.getUser1());
        playerManager.updatePlayer(winner);
        loser.setScore(calculatedScores.getUser2());
        playerManager.updatePlayer(loser);
    }

}
