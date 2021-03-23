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
                    .user1score(1000)
                    .user2score(1000)
                    .user1win(1)
                    .user2win(0)
                    .build(),
                    EloRatingCalculator.CalculatedScores.builder()
                    .user1(1008)
                    .user2(992)
                    .build()
            )
        );
    }
}