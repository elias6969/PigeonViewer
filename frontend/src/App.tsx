import { BrowserRouter, Routes, Route } from 'react-router-dom';
import HomePage from './components/HomePage';
import LoginPage from './components/LoginPage';
import FavoritesPage from './components/FavoritesPage';
import NavBar from './components/NavBar';
import RegisterPage from './components/RegisterPage';


function App() {
  return (
    <BrowserRouter>
      <NavBar /> {/* Stays full width */}
      <div className="app-wrapper">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/favorites" element={<FavoritesPage />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
