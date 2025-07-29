import './LoginPage.css';
import LoginForm from './LoginForm';

const LoginPage = () => {
  return (
    <div className="login-page">
      <h2 className="login-title">Login to PigeonFlix</h2>
      <LoginForm />
    </div>
  );
};

export default LoginPage;
