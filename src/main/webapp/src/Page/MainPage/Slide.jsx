import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Mousewheel, Keyboard, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/autoplay';
import './Slide.css';
import { useNavigate } from 'react-router-dom';
const Slide = () => {
  const navigate = useNavigate();
  const handleAddRecipe = () => {
    navigate('/recipeblog');
    };  
    const handleAddCalendar = () => {
      navigate('/dietcalendar');
      };  
  return (
    <>
      <Swiper
        cssMode={true}
        navigation={true}
        pagination={true}
        mousewheel={true}
        keyboard={true}
        autoplay={{ delay: 8000, disableOnInteraction: false }} // 자동 스크롤 설정 추가
        modules={[Navigation, Pagination, Mousewheel, Keyboard, Autoplay]} // Autoplay 모듈 추가
        className="mySwiper"
        slidesPerView={1}
        spaceBetween={30}
        loop={true}
        pagination={{
          clickable: true,
        }}
      >
        <SwiperSlide>
          <div>
            <img  className="S-img1"src="header_img.png" alt="img1" />
            <div className="slider-box">
                    <div className='S-text'>식단관리 및 도매소매값을 알려줍니다.</div>
                    <div className='S-text'>사용해보시겠습니까?</div>
                        <button onClick={handleAddCalendar} className="S-button">식단생성</button>
            </div>
          </div>
        </SwiperSlide>
        <SwiperSlide>
          <div>
            <img className="S-img1"src="header_img.png" alt="img2" />
            <div className="slider-box">
                    <div className='S-text'>식단관리 및 도매소매값을 알려줍니다.</div>
                    <div className='S-text'>사용해보시겠습니까?</div>
                        <button onClick={handleAddCalendar} className="S-button">식단생성</button>
            </div>
          </div>
        </SwiperSlide>
        <SwiperSlide>
        <div>
            <img className="S-img1"src="header_img.png" alt="img2" />
            <div className="slider-box">
                  <div className='S-text'>레시피 알려드립니다.</div>
                  <button onClick={handleAddRecipe} className="S-button">레시피게시판</button>

            </div>
            </div>
        </SwiperSlide>
      </Swiper>
    </>
  );
};

export default Slide;