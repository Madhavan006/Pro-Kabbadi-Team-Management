package com.examly.springapp.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String playerName;
    private String team;
    private String role;
    private int age;
    @JsonProperty(value = "totalPoints", access = JsonProperty.Access.READ_WRITE)
    private int totalPoints;
    
    @JsonProperty("points")
    public int getPoints() { return totalPoints; }
    
    @JsonProperty("points")
    public void setPoints(int points) { this.totalPoints = points; }
    
    public Player() {}
    
    public Player(String playerName, String team, String role, int age, int totalPoints) {
        this.playerName = playerName;
        this.team = team;
        this.role = role;
        this.age = age;
        this.totalPoints = totalPoints;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public int getTotalPoints() { return totalPoints; }
    public void setTotalPoints(int totalPoints) { this.totalPoints = totalPoints; }
}