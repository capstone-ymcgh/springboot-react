import React from "react";

const MenuPage = () => {
    return (
        <div>
            <nav className="topMenu">
                <a className='logo'>로고</a>
                <div>
                    <a to="/login" className="login">로그인</a>
                </div>
            </nav>
            
        </div>
    );
}

export default MenuPage;