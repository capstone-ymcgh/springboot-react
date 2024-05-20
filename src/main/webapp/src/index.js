import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import MainPage from './Page/MainPage/MainPage';
import CalendarPage from './Page/CalendarPage/CalendarPage';
import Board from './Page/Board';
import LoginPage from './Page/LoginPage/LoginPage';
import FristPage from './Page/FristPage/FristPage';
import Signup from './Page/LoginPage/SignupPage';
import RecipePage from './Page/recipePage/recipePage';
import RecipeBlog from './Page/recipeblog/recipeblog';
import SignupPage from './Page/LoginPage/SignupPage';
import App from './App';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
