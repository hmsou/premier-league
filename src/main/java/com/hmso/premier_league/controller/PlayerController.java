package com.hmso.premier_league.controller;

import com.hmso.premier_league.domain.Player;
import com.hmso.premier_league.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> findPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation){

        if (team != null && position != null){
            playerService.findPlayerByTeamAndPosition(team, position);
        }
        else if (team != null) {
            playerService.findPlayersFromTeam(team);
        }
        else if (name != null) {
            playerService.findPlayerByName(name);
        }
        else if (nation != null) {
            playerService.findPlayersByNation(nation);
        }
        else if (position != null) {
            playerService.findPlayersByPosition(position);
        }
        return playerService.findAll();
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player resultPlayer = playerService.updatePlayer(player);

        if (resultPlayer != null){
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player deleted", HttpStatus.OK);
    }
}
