package com.examly.springapp;

import com.examly.springapp.model.Player;
import com.examly.springapp.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private PlayerRepository playerRepository;
    
    @Override
    public void run(String... args) throws Exception {
        if (playerRepository.count() == 0) {
            playerRepository.save(new Player("Test Player", "Test Team", "Raider", 25, 100));
        }
    }
}