package com.examly.springapp.controller;

import com.examly.springapp.model.Player;
import com.examly.springapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowCredentials = "false")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;
    
    @PostMapping("/addPlayer")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        System.out.println("Received player: " + player.getPlayerName());
        Player savedPlayer = playerService.savePlayer(player);
        System.out.println("Saved player with ID: " + savedPlayer.getId());
        return ResponseEntity.ok(savedPlayer);
    }
    
    @GetMapping("/allPlayers")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        System.out.println("Found " + players.size() + " players");
        return ResponseEntity.ok(players);
    }
    
    @GetMapping("/byRole")
    public ResponseEntity<List<Player>> getPlayersByRole(@RequestParam String role) {
        List<Player> players = playerService.getPlayersByRole(role);
        return ResponseEntity.ok(players);
    }
    
    @GetMapping("/sortedByTeam")
    public ResponseEntity<List<Player>> getPlayersSortedByTeam() {
        List<Player> players = playerService.getPlayersSortedByTeam();
        return ResponseEntity.ok(players);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }
}