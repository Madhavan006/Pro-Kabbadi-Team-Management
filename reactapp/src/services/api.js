import axios from 'axios';

const API_BASE_URL = 'https://8080-bfabcadddbdfbaeebddbeceabcefefcd.premiumproject.examly.io/api/players';

export const addPlayer = async (player) => {
  try {
    console.log('Adding player:', player);
    const response = await axios.post(`${API_BASE_URL}/addPlayer`, player);
    console.log('Player added:', response.data);
    return response.data;
  } catch (error) {
    console.error('Add player error:', error);
    throw error;
  }
};

export const getAllPlayers = async () => {
  try {
    console.log('Calling API:', `${API_BASE_URL}/allPlayers`);
    const response = await axios.get(`${API_BASE_URL}/allPlayers`);
    console.log('API Response:', response.data);
    return response.data;
  } catch (error) {
    console.error('API Error:', error);
    throw error;
  }
};

export const getPlayersByRole = async (role) => {
  const response = await axios.get(`${API_BASE_URL}/byRole?role=${role}`);
  return response.data;
};

export const getPlayersSortedByTeam = async () => {
  const response = await axios.get(`${API_BASE_URL}/sortedByTeam`);
  return response.data;
};

export const deletePlayer = async (id) => {
  const response = await axios.delete(`${API_BASE_URL}/${id}`);
  return response.data;
};