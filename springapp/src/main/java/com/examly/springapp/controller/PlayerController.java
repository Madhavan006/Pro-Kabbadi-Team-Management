package com.examly.springapp.controller;

import com.examly.springapp.model.Player;
import com.examly.springapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8081"})
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;
    
    @PostMapping("/addPlayer")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player savedPlayer = playerService.savePlayer(player);
        return ResponseEntity.ok(savedPlayer);
    }
    
    @GetMapping("/allPlayers")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
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