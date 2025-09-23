import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import "@testing-library/jest-dom";
import App from "../App";

// ✅ Mock API services
jest.mock("../services/api", () => ({
  getAllPlayers: jest.fn(),
  getPlayersByRole: jest.fn(),
  getPlayersSortedByTeam: jest.fn(),
  addPlayer: jest.fn(),
  deletePlayer: jest.fn(),
}));

import {
  getAllPlayers,
  getPlayersByRole,
  getPlayersSortedByTeam,
  addPlayer,
  deletePlayer,
} from "../services/api";

describe("Pro Kabaddi Team Management", () => {
  const mockPlayers = [
    { id: 1, playerName: "Pardeep Narwal", team: "UP Yoddha", role: "Raider", age: 27, totalPoints: 1200 },
    { id: 2, playerName: "Manjeet Chhillar", team: "Tamil Thalaivas", role: "Defender", age: 36, totalPoints: 450 },
  ];

  beforeEach(() => {
    jest.clearAllMocks();
  });

  // ---------- Header ----------
  test("renders header title", async () => {
    getAllPlayers.mockResolvedValueOnce([]);
    render(<App />);
    expect(await screen.findByRole("heading", { name: /pro kabaddi team management/i })).toBeInTheDocument();
  });

  // ---------- Empty State ----------
  test("renders empty state when no players", async () => {
    getAllPlayers.mockResolvedValueOnce([]);
    render(<App />);
    expect(await screen.findByText(/no players found/i)).toBeInTheDocument();
  });

  // ---------- List Rendering ----------
  test("renders list of players", async () => {
    getAllPlayers.mockResolvedValueOnce(mockPlayers);
    render(<App />);
    expect(await screen.findByText("Pardeep Narwal")).toBeInTheDocument();
    expect(screen.getByText("Manjeet Chhillar")).toBeInTheDocument();
  });

  // ---------- Delete ----------
  test("deletes a player", async () => {
    getAllPlayers.mockResolvedValueOnce(mockPlayers);
    deletePlayer.mockResolvedValueOnce({});
    render(<App />);

    const deleteBtns = await screen.findAllByRole("button", { name: /delete/i });
    fireEvent.click(deleteBtns[0]);

    await waitFor(() => expect(deletePlayer).toHaveBeenCalledWith(1));
  });

  // ---------- Dropdown ----------
  test("renders default dropdown value Raider", async () => {
    getAllPlayers.mockResolvedValueOnce([]);
    render(<App />);
    expect(await screen.findByDisplayValue("Raider")).toBeInTheDocument();
  });

  test("changing dropdown updates role", async () => {
    getAllPlayers.mockResolvedValueOnce([]);
    render(<App />);
    const select = await screen.findByDisplayValue("Raider");
    fireEvent.change(select, { target: { value: "Defender" } });
    expect(select).toHaveValue("Defender");
  });

  // ---------- Delete Button Rendering ----------
  test("renders delete button for each player", async () => {
    getAllPlayers.mockResolvedValueOnce(mockPlayers);
    render(<App />);
    const deleteBtns = await screen.findAllByRole("button", { name: /delete/i });
    expect(deleteBtns).toHaveLength(2);
  });

  // ---------- EXTRA TEST CASES ----------
  test("renders player team name", async () => {
    getAllPlayers.mockResolvedValueOnce(mockPlayers);
    render(<App />);
    expect(await screen.findByText("UP Yoddha")).toBeInTheDocument();
    expect(screen.getByText("Tamil Thalaivas")).toBeInTheDocument();
  });

  test("renders player role", async () => {
    getAllPlayers.mockResolvedValueOnce(mockPlayers);
    render(<App />);
    expect(await screen.findByText("Raider")).toBeInTheDocument();
    expect(screen.getByText("Defender")).toBeInTheDocument();
  });

  test("renders player age", async () => {
    getAllPlayers.mockResolvedValueOnce(mockPlayers);
    render(<App />);
    expect(await screen.findByText(/27/i)).toBeInTheDocument();
    expect(screen.getByText(/36/i)).toBeInTheDocument();
  });

  test("renders player total points", async () => {
    getAllPlayers.mockResolvedValueOnce(mockPlayers);
    render(<App />);
    expect(await screen.findByText(/1200/i)).toBeInTheDocument();
    expect(screen.getByText(/450/i)).toBeInTheDocument();
  });
});
