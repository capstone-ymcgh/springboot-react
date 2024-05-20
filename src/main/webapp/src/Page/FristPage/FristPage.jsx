import React from 'react';
import './FristPage.css';
import { useNavigate } from 'react-router-dom';


const Navigation = () => {

    const navigate = useNavigate();

    const clickhome = () => {
        navigate('/');
    }

    const handleAddRecipe = () => {
    navigate('/login');
    };  

    return (
        <div>
            <nav className="topMenu">
                <div onClick={clickhome} className='logo'>
                    <img className="logo-img"src="/logo.png" alt="로고" />
                </div>
                <div>
                <div onClick={handleAddRecipe}  className="login">로그인</div>
                </div>
            </nav>  
            <div className="main">
                <div className="container">
                    <div>식단관리 및 도매소매값을 알려줍니다.</div>
                    <div>사용해보시겠습니까?</div>
                    <div>
                        <button onClick={handleAddRecipe} className="sign">가입하기</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Navigation;

