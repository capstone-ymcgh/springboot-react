import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './topmenu.css';

const TopMenu = () => {
    const navigate = useNavigate();
    const [menu, setMenu] = useState("home");
    const clickhome = () => {
        navigate('/');
    }
    const clickdietcalendar = () => {
        navigate('/dietcalendar');
    }
    const clickdierecipebelog = () => {
        navigate('/recipeblog');
    }
    const clickUserInfo = () => {
        navigate('/userinfo');
    }
    return (
        <div className="topMenu">
            <div className="leftSection">
                <div onClick={clickhome} className='logo'>
                    <img className="logo-img"src="/logo.png" alt="로고" />
                </div>
            </div>
            <div className="rightSection">
                <ul className='tpMenu'>
                    <li onClick={clickhome}className={menu==="home"?"active":""}>메인</li>
                    <li onClick={clickdietcalendar} className={menu==="Calendar"?"active":""}>식단짜주기</li>
                    <li onClick={clickdierecipebelog} className={menu==="Blog"?"active":""}>레시피게시판</li>
                </ul>
                <a onClick={clickUserInfo} className="login">내정보</a>
            </div>
        </div>
    );
};

export default TopMenu;