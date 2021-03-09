package hu.iit.uni.miskolc.advanced.java.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Player {

    private String name;

    @EqualsAndHashCode.Exclude
    private int score;

}
