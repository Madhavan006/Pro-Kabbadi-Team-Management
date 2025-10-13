import React from 'react';
import { deletePlayer } from '../services/api';

const PlayerList = ({ players, onDelete }) => {
  const handleDelete = async (id) => {
    try {
      await deletePlayer(id);
      onDelete();
    } catch (error) {
      console.error('Error deleting player:', error);
    }
  };

  if (players.length === 0) {
    return <div>No players found</div>;
  }

  return (
    <div className="player-list">
      {players.map((player) => (
        <div key={player.id} className="player-card">
          <h3>{player.playerName}</h3>
          <p>{player.team}</p>
          <p>Role: {player.role}</p>
          <p>Age: {player.age}</p>
          <p>Total Points: {player.totalPoints}</p>
          <button onClick={() => handleDelete(player.id)}>Delete</button>
        </div>
      ))}
    </div>
  );
};

export default PlayerList;