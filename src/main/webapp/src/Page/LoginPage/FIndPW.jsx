import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css';

export default function FindPW() {
  const [email, setEmail] = useState('');
  const navigate = useNavigate();

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // 이메일 보내기 로직을 여기에 추가하세요.
    // 이메일이 성공적으로 보내진 후, 사용자를 로그인 페이지로 리다이렉트합니다.
    navigate('/login');
  };

  return (
    <div className='page-c'>
      <div className="page-S">
        <img src="logo.png" alt="logo" className="logo-a" />
        <div className="titleWrap">
          비밀번호를 찾기 위해 이메일을 입력해주세요
        </div>
        <div className="contentWrap">
          <div className="inputTitle">이메일</div>
          <div className="inputWrap">
            <form onSubmit={handleSubmit}>
              <input
                className="input_text"
                type="email"
                placeholder="이메일"
                value={email}
                onChange={handleEmailChange}
                required
              />
              <button type="submit" className="bottomButton">비밀번호 찾기</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}