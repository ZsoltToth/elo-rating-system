package hu.iit.uni.miskolc.advanced.java.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EloRatingCalculatorImplTest {

    private EloRatingCalculatorImpl eloRatingCalculator;

    @BeforeEach
    void setUp(){
       eloRatingCalculator = new EloRatingCalculatorImpl();
    }

    @ParameterizedTest
    @MethodSource("provideScoreCalculations")
    void calculateScores(
            EloRatingCalculator.GameResult gameResult,
            EloRatingCalculator.CalculatedScores calculatedScores) {
        //given
        //when
        EloRatingCalculator.CalculatedScores actual = eloRatingCalculator.calculateScores(gameResult);
        //then
       assertThat(actual).isEqualTo(calculatedScores);
    }

    private static Stream<Arguments> provideScoreCalculations(){
        return Stream.of(
            Arguments.of(
                    EloRatingCalculator.GameResult.builder()
                    .winnerOriginalScore(1000)
                    .loserOriginalScore(1000)
                    .build(),
                    EloRatingCalculator.CalculatedScores.builder()
                    .winnerUpdatedScore(1008)
                    .loserUpdatedScore(992)
                    .build()
            )
        );
    }
}