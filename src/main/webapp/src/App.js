import './App.css';
import LoginPage from './Page/LoginPage/LoginPage';
import FristPage from './Page/FristPage/FristPage';
import MainPage from './Page/MainPage/MainPage';
import RecipePage from './Page/recipePage/recipePage';
import FimdId from './Page/LoginPage/FindId';
import Findpw from './Page/LoginPage/FIndPW'; // Findpw import // Findpw import
import NewPage from './Page/NewPage'; // NewPage import
import RecipeBlog from './Page/recipeblog/recipeblog'; // RecipeBlog import
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Signup from './Page/LoginPage/SignupPage';
import Slide from './Page/MainPage/Slide';
import Recipe from './Page/recipe/recipe'; 
import UserInfo from './Page/UserInfo/UserInfo';
import Dietcalendar from './Page/Dietcalendar/Dietcalendar'; // Dietcalendar import
import CalendarPage from './Page/CalendarPage/CalendarPage'; // CalendarPage import

// Findpw import
function App() {
  return (
    <div className='App'>
      <BrowserRouter>
          <Routes>
            <Route path="/userinfo" element={<UserInfo />} />
            <Route path="/findpw" element={<Findpw />} /> {/* Add this line */}
            <Route path="/calendar" element={<CalendarPage />} />
            <Route path="/dietcalendar" element={<Dietcalendar />} />
            <Route path="/slide" element={<Slide />} />
            <Route path="/recipe" element={<Recipe />} />
            <Route path="/" element={<FristPage />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/main" element={<MainPage />} />
            <Route path="/new" element={<NewPage />} />
            <Route path="/recipePage" element={<RecipePage />} />
            <Route path="/recipeblog" element={<RecipeBlog />} /> {/* Add this line */}
            <Route path="/findid" element={<FimdId />} />
          </Routes>
      </BrowserRouter>
    </div>
  );
  
}

export default App;