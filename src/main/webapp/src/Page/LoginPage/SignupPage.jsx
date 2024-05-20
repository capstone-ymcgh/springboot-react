import React ,{ useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';

export default function Signup(){


  const navigate = useNavigate();

    const handleAddlogin = () => {
    navigate('/login');
    };  
    // 비밀번호와 재입력 비밀번호 상태를 관리하는 state
const [pw, setPw] = useState('');
const [rpw, setrPw] = useState('');

// 비밀번호와 재입력 비밀번호의 유효성 상태를 관리하는 state
const [rpwValid, setrPwValid] = useState(false);
const [pwValid, setPwValid] = useState(false);

// 비밀번호와 재입력 비밀번호가 유효하지 않을 경우를 관리하는 state
const [notAllow, setNotAllow] = useState(true);

// 선택된 값을 관리하는 state
const [selectValue, setSelectValue] = useState('1')

// 비밀번호와 재입력 비밀번호의 유효성 상태가 변경될 때마다 실행되는 useEffect 훅
useEffect(() => {
    if(rpwValid && pwValid) {
      setNotAllow(false);
      return;
    }
    setNotAllow(true);
}, [rpwValid, pwValid]);

// 비밀번호 입력을 처리하는 핸들러 함수
const handlePw = (e) => {
    setPw(e.target.value);
    const regex = /^(?=.*[!@#$%^&*.])/;
    if (e.target.value.length >= 8 && regex.test(e.target.value)) {
      setPwValid(true);
    } else {
      setPwValid(false);
    }
};

// 재입력 비밀번호 입력을 처리하는 핸들러 함수
const handlerPw = (e) => {
    setrPw(e.target.value);
    const regex = /^(?=.*[!@#$%^&*.])/;
    if (e.target.value.length >= 8 && regex.test(e.target.value)) {
      setrPwValid(true);
    } else {
      setrPwValid(false);
    }
};

// 선택된 값을 처리하는 핸들러 함수
const handleSelectChange = (e) => {
    setSelectValue(e.target.value);
}
    return (
        <div className='page-c'>
        <div className="page-S">
        <img src="logo.png" alt="logo" className="logo-a" />
            <div className="titleWrap">
                회원가입
            </div>
            <div className="contentWrap">
                <div >
                <div className="inputTitle">
                            닉네임을 입력해주세요
                    </div>
                    <div className="inputWrap">
                    <input
                        type="email"
                        placeholder="닉네임"
                        className="input_text"
                    />
                    </div>
                    <div style={{ marginTop: "10px" }} className="inputTitle">
                            이메일을 입력해주세요
                    </div>
                    <div className="inputWrap">
                    <input
                        type="email"
                        placeholder="이메일"
                        className="input_text"
                    />
                    </div>
                    <div style={{ marginTop: "10px" }} className="inputTitle">
                            비밀번호를 입력해주세요
                    </div>
                    <div className="inputWrap">
                            
                    <input
                        type="password"
                        placeholder="비밀번호"
                        className="input_text"
                        value={pw}
                        onChange={handlePw}
                    />
                    </div>
                    <div className="errorMessageWrap">
                        {!pwValid && pw.length > 0 && (
                        <div>영문, 숫자, 특수문자 포함 8자 이상 입력해주세요.</div>
                        )}
                    </div>

                    <div style={{ marginTop: "10px" }} className="inputTitle">
                            비밀번호 확인
                    </div>
                    <div className="inputWrap">
                    <input
                        type="password"
                        placeholder="비밀번호 확인"
                        className="input_text"
                        value={rpw}
                        onChange={handlerPw}
                    />
                    </div>
                    <div className="errorMessageWrap">
                        {!(pwValid === rpwValid) && rpw.length > 0 &&(
                            <div>비밀번호가 다릅니다..</div>
                        )}
                    </div>
                    <div >
                    <div className="inputTitle">
                            사용자 유형을 선택해주세요
                        </div>
                    <select value={selectValue} onChange={handleSelectChange} className="selectWrap">
                        <option value="1">선택해주세요</option>
                        <option value="2">일반</option>
                        <option value="3">도매상</option>
                        </select>
                    </div>

                </div>
                <button onClick={handleAddlogin}  className="SbottomButton">
                    가입하기
                </button>
                </div>

        </div>
        </div>

    );

}