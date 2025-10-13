import React, { useState } from 'react';
import { addPlayer } from '../services/api';

const PlayerForm = ({ onAdd }) => {
  const [formData, setFormData] = useState({
    playerName: '',
    team: '',
    role: 'Raider',
    age: '',
    totalPoints: ''
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addPlayer({
        ...formData,
        age: parseInt(formData.age),
        totalPoints: parseInt(formData.totalPoints)
      });
      alert('Player added successfully!');
      onAdd();
      setFormData({
        playerName: '',
        team: '',
        role: 'Raider',
        age: '',
        totalPoints: ''
      });
    } catch (error) {
      console.error('Error adding player:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        name="playerName"
        placeholder="Player Name"
        value={formData.playerName}
        onChange={handleChange}
        required
      />
      <input
        type="text"
        name="team"
        placeholder="Team"
        value={formData.team}
        onChange={handleChange}
        required
      />
      <select
        name="role"
        value={formData.role}
        onChange={handleChange}
        required
      >
        <option value="Raider">Raider</option>
        <option value="Defender">Defender</option>
        <option value="All-rounder">All-rounder</option>
      </select>
      <input
        type="number"
        name="age"
        placeholder="Age"
        value={formData.age}
        onChange={handleChange}
        required
      />
      <input
        type="number"
        name="totalPoints"
        placeholder="Total Points"
        value={formData.totalPoints}
        onChange={handleChange}
        required
      />
      <button type="submit">Add Player</button>
    </form>
  );
};

export default PlayerForm;