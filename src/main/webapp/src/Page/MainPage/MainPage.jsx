import React, { useEffect, useRef } from 'react';
import TopMenu from '../topmenu/topmenu';
import Slide from './Slide';
import './MainPage.css';
import MennuSlide from './menuSlide';
function MainPage() {

    return (
        <div>
            <TopMenu />
            <Slide/>
            <div>
                <div>추천레시피</div>
                <div>추천하는 레시피로 최고의 순간을 만들어보세요</div>
                <div className='Recipe-recommendation'>
                    <div className='r1'>
                        <img className="r1-img"src="header_img.png" alt="img1" />
                        <div className='r1-text'>제목</div>
                    </div>
                    <div className='r2'>
                        <img className="r1-img"src="header_img.png" alt="img1" />
                        <div className='r1-text'>제목</div>
                    </div>
                </div>
            </div>
            <div>
                <div>인기 레시피</div>
            <MennuSlide/>
            </div>
        </div>
    );
}

export default MainPage;