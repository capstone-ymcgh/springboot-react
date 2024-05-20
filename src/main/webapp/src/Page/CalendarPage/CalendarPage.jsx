import React, { useEffect, useState } from 'react';
import TopMenu from '../topmenu/topmenu';
import './CalendarPage.css';
import { useLocation,useNavigate } from 'react-router-dom';


const months = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
function CalendarPage() {
    const location = useLocation();
    const [date, setDate] = useState(new Date());
    const [currYear, setCurrYear] = useState(date.getFullYear());
    const [currMonth, setCurrMonth] = useState(date.getMonth());
    const [days, setDays] = useState([]);
    const [showSelectMenu, setShowSelectMenu] = useState(false);
    const [selectedDate, setSelectedDate] = useState({ month: '', day: '' });
    const [inputValue, setInputValue] = useState('');
    const navigate = useNavigate();


    const diet = { //식단 입력받는곳
        menu1: 'menu1',
        menu2: 'menu2',
        menu3: 'menu3',
        menu4: 'menu4',
        menu5: 'menu5',
        menu6: 'menu6',
        calorie: 'calorie',
        carbohydrate:'carbohydrate', 
        protein:'protein',
        fat:'fat'
    };

    useEffect(() => {
        if (location.state && location.state.selectedDate) {
            const [year, month] = location.state.selectedDate.split('/');
            setCurrYear(parseInt(year));
            setCurrMonth(parseInt(month) - 1); // getMonth는 0부터 시작하므로 1을 빼줍니다.
        }

        const renderCalendar = () => {
            let lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate();
            let firstDayofMonth = new Date(currYear, currMonth, 1).getDay();
            let lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay();
            let lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate();
            
            let daysArray = [];
            for (let i = firstDayofMonth; i > 0; i--) {
                daysArray.push({ day: lastDateofLastMonth - i + 1, isActive: false });
            }

            for (let i = 1; i <= lastDateofMonth; i++) {
                daysArray.push({ day: i, isActive: false, isCurrentMonth: true });
            }

            for (let i = lastDayofMonth; i < 6; i++) {
                daysArray.push({ day: i - lastDayofMonth + 1, isActive: false });
            }

            setDays(daysArray);
        };

        renderCalendar();
    }, [location, currMonth, currYear]);

    const handleNavigation = (direction) => {
        let newMonth = direction === 'prev' ? currMonth - 1 : currMonth + 1;
        let newYear = currYear;
        if (newMonth < 0) {
            newYear = currYear - 1;
            newMonth = 11;
        } else if (newMonth > 11) {
            newYear = currYear + 1;
            newMonth = 0;
        }
        setCurrYear(newYear);
        setCurrMonth(newMonth);
        setDate(new Date(newYear, newMonth));
    };
    const handleDayClick = (day) => {
        if (day.isCurrentMonth) {
            // 이번 달의 날짜를 클릭했을 때 sellectmenu 창을 표시합니다.
            setShowSelectMenu(true);
            setSelectedDate({ month: months[currMonth], day: day.day });
        }
    };
    const handleCloseClick = () => {
        setShowSelectMenu(false);
    };

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };
    const handleLeftButtonClick = () => {
        setSelectedDate(prevDate => {
            if (prevDate.day === 1) {
                return prevDate;
            }    
            return {
                ...prevDate,
                day: prevDate.day - 1
            };
        });
    };
    const handleRightButtonClick = () => {
        setSelectedDate(prevDate => {
            // 현재 월의 마지막 날짜를 얻습니다.
            const lastDayOfMonth = new Date(currYear, currMonth + 1, 0).getDate();
            
            if (prevDate.day >= lastDayOfMonth) {
                // 현재 값이 마지막 날짜 이상이므로 값을 변경하지 않습니다.
                return prevDate;
            }
    
            return {
                ...prevDate,
                day: prevDate.day + 1
            };
        });
    };
    const savemenu = () => {
        // 저장하는 로직을 구현합니다.
        setShowSelectMenu(false);


    };
    const SaveCalendar = () => {
        // 저장하는 로직을 구현합니다.
    
        // 저장 후 DietCalendar 페이지로 이동
        navigate('/dietcalendar');
    };

    


    return (
        <div>
        <TopMenu />
        <div className="wrapper">
            
            <header>
                <div className="nav">
                    <p className="current-date">{`${currYear}년 ${months[currMonth]}`}</p>
                    <div>제목을 입력하세요:<input type="text" value={inputValue} onChange={handleInputChange} /></div>
                </div>
            </header>
            
            <div className="calendar">
                <div className="weeks">
                    <div >일</div>
                    <div >월</div>
                    <div >화</div>
                    <div >수</div>
                    <div >목</div>
                    <div >금</div>
                    <div >토</div>
                </div>
                <div className="days">
                    {days.map((day, index) => (
                        <div key={index} href="#" onClick={() => handleDayClick(day)} className={`${day.isActive ? 'active' : day.isCurrentMonth
                        ? 'current-month' :  'inactive'} ${!day.isCurrentMonth ? 'non-current-month' : ''}`}>
                            {day.day}     
                            <div className='menu'>
                                <div>{diet.menu1}</div>
                                <div>{diet.menu2}</div>
                                <div>{diet.menu3}</div>
                                <div>{diet.menu4}</div>
                                <div>{diet.menu5}</div>
                                <div>{diet.menu6}</div>
                                <div>{diet.calorie}</div>
                            </div>                        
                        </div>
                    ))}
                    
                </div>
            </div>
            <div>
                <button className='Button-save' onClick={SaveCalendar}>식단 저장하기</button>
            </div>
            {showSelectMenu && <div className='sellectmenu'>
            <button className='Close' onClick={handleCloseClick}>X</button>
            <div className='MD'>
            <button className='Left-B'onClick={handleLeftButtonClick}>&lt;</button>
                {selectedDate.month} {selectedDate.day}일                
                <button className='Right-B'onClick={handleRightButtonClick}>&gt;</button>
            </div>
            <div className='MenuContainer'>
                <div className='MenuText'>
                <div>{diet.menu1}</div>
                <div>{diet.menu2}</div>
                <div>{diet.menu3}</div>
                <div>{diet.menu4}</div>
                <div>{diet.menu5}</div>
                <div>{diet.menu6}</div>
                <div>{diet.calorie}</div>

                </div>
                <div className='MenuSearch'>
                    <input className='InputText'type='text' placeholder='음식을 입력하세요'/>
                    <button className='textsearch'></button>
                    <div className='SearchData'></div>
                </div>
                
            </div>
            <div >
                    <button className='Button-ss'onClick={savemenu}>수정하기</button>
            </div>
                
            </div>}
        </div>
        </div>
        
        
    );
}

export default CalendarPage;