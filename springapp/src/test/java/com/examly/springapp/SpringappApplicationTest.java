package com.examly.springapp;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import java.io.File;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringappPlayerTests {

    @Autowired
    private MockMvc mockMvc;

    // ---------- Core API Tests ----------

    @Order(1)
    @Test
    void AddPlayerReturns200() throws Exception {
        String playerData = """
                {
                    "playerName": "Pardeep Narwal",
                    "team": "UP Yoddhas",
                    "role": "Raider",
                    "age": 27,
                    "points": 1200
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/players/addPlayer")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerData)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.playerName").value("Pardeep Narwal"))
                .andExpect(jsonPath("$.team").value("UP Yoddhas"))
                .andReturn();
    }

    @Order(2)
    @Test
    void GetAllPlayersReturnsArray() throws Exception {
        mockMvc.perform(get("/api/players/allPlayers")
                        .with(jwt())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Order(3)
    @Test
    void GetPlayersByRoleReturns200() throws Exception {
        mockMvc.perform(get("/api/players/byRole")
                        .with(jwt())
                        .param("role", "Raider")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerName").exists())
                .andReturn();
    }

    @Order(4)
    @Test
    void GetPlayersSortedByTeamReturns200() throws Exception {
        mockMvc.perform(get("/api/players/sortedByTeam")
                        .with(jwt())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

    @Order(5)
    @Test
    void DeletePlayerReturns200() throws Exception {
        // First add a player to delete
        String playerData = """
                {
                    "playerName": "Temp Player",
                    "team": "Test Team",
                    "role": "Defender",
                    "age": 25,
                    "points": 500
                }
                """;

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/players/addPlayer")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(playerData)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Extract ID from response
        Long id = com.fasterxml.jackson.databind.JsonNode.class
                .cast(new com.fasterxml.jackson.databind.ObjectMapper().readTree(response))
                .get("id").asLong();

        // Delete it
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/players/" + id)
                        .with(jwt()))
                .andExpect(status().isOk())
                .andReturn();
    }

    // ---------- Project Structure Tests ----------

    @Test
    void ControllerDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/controller";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void PlayerControllerFileExists() {
        String filePath = "src/main/java/com/examly/springapp/controller/PlayerController.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    void ModelDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/model";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void PlayerModelFileExists() {
        String filePath = "src/main/java/com/examly/springapp/model/Player.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    void RepositoryDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/repository";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void ServiceDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/service";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void PlayerServiceClassExists() {
        checkClassExists("com.examly.springapp.service.PlayerService");
    }

    @Test
    void PlayerModelClassExists() {
        checkClassExists("com.examly.springapp.model.Player");
    }

    @Test
    void PlayerModelHasPlayerNameField() {
        checkFieldExists("com.examly.springapp.model.Player", "playerName");
    }

    @Test
    void PlayerModelHasTeamField() {
        checkFieldExists("com.examly.springapp.model.Player", "team");
    }

    @Test
    void PlayerModelHasRoleField() {
        checkFieldExists("com.examly.springapp.model.Player", "role");
    }

    @Test
    void PlayerModelHasAgeField() {
        checkFieldExists("com.examly.springapp.model.Player", "age");
    }

    @Test
    void PlayerModelHasTotalPointsField() {
        checkFieldExists("com.examly.springapp.model.Player", "totalPoints");
    }
    

    @Test
    void PlayerRepoExtendsJpaRepository() {
        checkClassImplementsInterface("com.examly.springapp.repository.PlayerRepository",
                "org.springframework.data.jpa.repository.JpaRepository");
    }

    // ---------- Helpers ----------

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interfaceClazz = Class.forName(interfaceName);
            assertTrue(interfaceClazz.isAssignableFrom(clazz));
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " or interface " + interfaceName + " does not exist.");
        }
    }
}
