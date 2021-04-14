package hu.iit.uni.miskolc.advanced.java.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerDto {

    private String name;

    private double score;
}
