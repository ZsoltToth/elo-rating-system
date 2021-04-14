package hu.iit.uni.miskolc.advanced.java.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.iit.uni.miskolc.advanced.java.controller.dto.PlayerDto;
import hu.iit.uni.miskolc.advanced.java.model.Player;
import hu.iit.uni.miskolc.advanced.java.service.PlayerManager;
import hu.iit.uni.miskolc.advanced.java.service.exception.PlayerAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerController {

    private final PlayerManager service;

    /**
     * Get method.
     *
     * @return player objects
     */
    @GetMapping("/")
    public Collection<PlayerDto> fetchAllPlayers() {
        return service.fetchPlayers()
                .stream()
                .map(player -> PlayerDto.builder()
                        .name(player.getName())
                        .score(player.getScore())
                        .build()
                )
                .collect(Collectors.toList());
    }

    /**
     * Get method.
     *
     * @return player objects
     */
    @GetMapping("/string")
    public String fetchAllPlayersAsString() throws JsonProcessingException {
        Collection<PlayerDto> result =  service.fetchPlayers()
                .stream()
                .map(player -> PlayerDto.builder()
                        .name(player.getName())
                        .score(player.getScore())
                        .build()
                )
                .collect(Collectors.toList());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }



    /**
     * Record a player.
     *
     * @param playerName name of the player
     */
    @PostMapping("/")
    public void reqister(@RequestParam String playerName) {
        try {
            service.register(new Player(playerName, 1000));
        } catch (PlayerAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
