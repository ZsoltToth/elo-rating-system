package hu.iit.uni.miskolc.advanced.java.service;

import hu.iit.uni.miskolc.advanced.java.model.Player;

/**
 * https://en.wikipedia.org/wiki/Elo_rating_system
 */
public interface EloRatingService {

    void updatePlayerRankings(Player user, Player opponent, int userWin, int opponentWin);
}
