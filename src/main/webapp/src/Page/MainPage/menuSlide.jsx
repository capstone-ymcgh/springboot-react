import React, { useRef } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';

import './menuSlide.css';

import { Pagination, Navigation } from 'swiper/modules';

const MenuSlide = () => {
  const swiperRef = useRef(null);

  return (
    <div className='menuSlide-contanier'>
    <Swiper
      onSwiper={(swiper) => { swiperRef.current = swiper; }}
      slidesPerView={5}
      slidesPerGroup={5}
      centeredSlides={false}
      spaceBetween={30}
      pagination={{
        type: 'fraction',
      }}
      navigation={true}
      modules={[Pagination, Navigation]}
      className="menuSlide-swiper"
      initialSlide={0}
      loop={true}
    >
      <SwiperSlide className='menu-slide'>Slide 1</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 2</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 3</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 4</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 5</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 6</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 7</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 8</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 9</SwiperSlide>
      <SwiperSlide className='menu-slide'>Slide 10</SwiperSlide>
    </Swiper>
    </div>
  );
}

export default MenuSlide;