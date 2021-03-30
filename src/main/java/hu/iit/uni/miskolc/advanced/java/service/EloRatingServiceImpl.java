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
                        loser.getScore()
                ));
        winner.setScore(calculatedScores.getWinnerUpdatedScore());
        playerManager.updatePlayer(winner);
        loser.setScore(calculatedScores.getLoserUpdatedScore());
        playerManager.updatePlayer(loser);
    }

}
