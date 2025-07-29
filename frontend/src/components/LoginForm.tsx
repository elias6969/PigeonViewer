import { useState } from 'react';
import axios from 'axios';


const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', {
        username,
        password,
      });

      const token = response.data.token;
      localStorage.setItem('jwt', token);
      alert('Login successful!');
    } catch (err) {
      alert('Login failed!');
    }
  };

  return (
    <form className="login-form" onSubmit={handleLogin}>
      <input
        className="login-input"
        value={username}
        onChange={e => setUsername(e.target.value)}
        placeholder="Username"
      />
      <input
        className="login-input"
        type="password"
        value={password}
        onChange={e => setPassword(e.target.value)}
        placeholder="Password"
      />
      <button className="login-button" type="submit">Login</button>
    </form>
  );
};


export default LoginForm;
