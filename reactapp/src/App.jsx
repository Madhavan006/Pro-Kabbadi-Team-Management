import React, { useState, useEffect } from 'react';
import Header from './components/Header';
import PlayerForm from './components/PlayerForm';
import PlayerList from './components/PlayerList';
import { getAllPlayers, getPlayersByRole, getPlayersSortedByTeam } from './services/api';
import './App.css';

function App() {
  const [players, setPlayers] = useState([]);
  const [filter, setFilter] = useState('all');

  const fetchPlayers = async () => {
    try {
      let data;
      if (filter === 'all') {
        data = await getAllPlayers();
      } else if (filter === 'team') {
        data = await getPlayersSortedByTeam();
      } else {
        data = await getPlayersByRole(filter);
      }
      setPlayers(data);
    } catch (error) {
      console.error('Error fetching players:', error);
    }
  };

  useEffect(() => {
    fetchPlayers();
  }, [filter]);

  const handleFilterChange = (e) => {
    setFilter(e.target.value);
  };

  return (
    <div className="App">
      <Header />
      <div className="filter-section">
        <select value={filter} onChange={handleFilterChange}>
          <option value="all">All Players</option>
          <option value="Raider">Raiders</option>
          <option value="Defender">Defenders</option>
          <option value="All-rounder">All-rounders</option>
          <option value="team">Sort by Team</option>
        </select>
      </div>
      <PlayerForm onAdd={fetchPlayers} />
      <PlayerList players={players} onDelete={fetchPlayers} />
    </div>
  );
}

export default App;