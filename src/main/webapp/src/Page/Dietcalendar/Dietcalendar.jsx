import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import "./Dietcalendar.css";
import TopMenu from "../topmenu/topmenu";
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

const Dietcalendar = () => {
    const [dietcalendarData, setDietcalendarData] = useState([]); // dietcalendarData 상태 추가

    const handleAddDiet = () => {
        const newDiet = {
            id: dietcalendarData.length, // 새로운 식단의 id는 현재 식단 데이터의 길이로 설정
            name: `제목 ${dietcalendarData.length + 1}`, // 새로운 식단의 이름 설정
            data:'날짜',
            calorie:'평균 칼로리'
        };
        setDietcalendarData([...dietcalendarData, newDiet]); // 새로운 식단을 dietcalendarData에 추가
    };
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 16;

    const pages = Math.ceil(dietcalendarData.length / itemsPerPage);
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const currentItems = dietcalendarData.slice(startIndex, endIndex);

    const handleNextPage = () => {
        if (currentPage + 10 <= pages) {
            setCurrentPage(currentPage + 10);
        } else {
            setCurrentPage(pages);
        }
    };

    const handlePrevPage = () => {
        if (currentPage - 10 >= 1) {
            setCurrentPage(currentPage - 10);
        } else {
            setCurrentPage(1);
        }
    };
    const handlePageClick = (page) => {
        setCurrentPage(page);
    };

    const startPage = Math.floor((currentPage - 1) / 10) * 10 + 1;
const endPage = Math.min(startPage + 9, pages);

const pageNumbers = [];
for (let i = startPage; i <= endPage; i++) {
    pageNumbers.push(i);
}

const navigate = useNavigate();
const [modalOpen, setModalOpen] = useState(false);
const [selectedDate, setSelectedDate] = useState(new Date());

const handleOpenModal = () => {
    setModalOpen(true);
};

const handleCloseModal = () => {
    setModalOpen(false);
};

const handleDateChange = (date) => {
    setSelectedDate(date);
};

const handleGoToCalendar = () => {
    const year = selectedDate.getFullYear();
    const month = selectedDate.getMonth() + 1; // getMonth는 0부터 시작하므로 1을 더해줍니다.
    const formattedDate = `${year}/${month}`;
    navigate('/calendar', { state: { selectedDate: formattedDate } });
    setModalOpen(false);
    console.log('calendar', year, month);
};
const openCalendar = () => {
    //생성된 식단생성 게시판을 클릭하면 calendar로 이동
}


    return (
        <div>
            <TopMenu />
            <div className="dietcalendar">
                <div>
                <div>식단생성 게시판</div>
                <button className="dietcalendar-add" onClick={handleOpenModal}>식단추가</button>
                <button className="dietcalendar-add" onClick={handleAddDiet}>식단추가</button>
                    {modalOpen && (
                        <div className="modal">
                            <div className="modal-content">
                            <button className='model-close' onClick={handleCloseModal}>X</button>
                            <div className='modal-text'>식단을 생성할 월을 선택해주세요</div>
                            <DatePicker selected={selectedDate} onChange={handleDateChange} dateFormat="yyyy/MM" showMonthYearPicker />
                            <button className='modal-text' onClick={handleGoToCalendar}>확인</button>
                            </div>
                        </div>
                    )}
                </div>
                <div className="dietcalendar-list" onClick={openCalendar}>
        {currentItems.map((diet) => (
            <div key={diet.id} className="dietcalendar-item">
                <div>{diet.name}</div>
                <div>{diet.data}</div>
                <div>{diet.calorie}</div>
            </div>
        ))}
    </div>
                <div className='dietcalendar-bottom'>
                <button className="dietcalendar-button"onClick={handlePrevPage}>&lt;</button>
                    {pageNumbers.map((page) => (
                    <button className="dietcalendar-button" key={page} onClick={() => handlePageClick(page)}>{page}</button>
                    ))}
                    <button className="dietcalendar-button" onClick={handleNextPage}>&gt;</button>
                    </div>
            </div>
            
        </div>
    );
};

export default Dietcalendar;