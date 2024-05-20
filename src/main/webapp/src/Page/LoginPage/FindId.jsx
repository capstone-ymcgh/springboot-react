import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function FindIdPage() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');

  const handleFindId = () => {
    // 아이디 찾기 로직을 여기에 구현하세요.
    // 예를 들어, 이메일을 서버에 보내고, 해당 이메일로 아이디를 찾아서 이메일로 보낼 수 있습니다.
    // 이 로직은 서버와의 통신이 필요하므로, 실제 구현은 백엔드 개발자와 협의가 필요합니다.
  };

  return (
    <div className='page-c'>
      <div className="page-S">
      <img src="logo.png" alt="logo" className="logo-a" />
        <div className="titleWrap">
          아이디 찾기
        </div>
        <div className="contentWrap">
          <div className="inputTitle">
            등록된 이메일을 입력해주세요
          </div>
          <div className="inputWrap">
            <input
              type="email"
              placeholder="이메일"
              className="input_text"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <button onClick={handleFindId} className="SbottomButton">
            아이디 찾기
          </button>
        </div>
      </div>
    </div>
  );
}