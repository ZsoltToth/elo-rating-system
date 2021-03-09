package hu.iit.uni.miskolc.advanced.java.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Player {

    private String name;

    @EqualsAndHashCode.Exclude
    private int score;

}
