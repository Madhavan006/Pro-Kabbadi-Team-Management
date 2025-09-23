package com.examly.springapp.service;

import com.examly.springapp.model.Player;
import com.examly.springapp.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }
    
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    
    public List<Player> getPlayersByRole(String role) {
        return playerRepository.findByRole(role);
    }
    
    public List<Player> getPlayersSortedByTeam() {
        return playerRepository.findAllByOrderByTeamDesc();
    }
    
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}