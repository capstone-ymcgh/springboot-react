import React, { useEffect, useState } from 'react'
import './LoginPage.css';
import { useNavigate } from 'react-router-dom';



export default function Login() {

    
    const [email, setEmail] = useState('');
    const [pw, setPw] = useState('');
    const User = {
      email: '1234@naver.com',
      pw: 'qkr123@@'
    }

    const [emailValid, setEmailValid] = useState(false);
    const [pwValid, setPwValid] = useState(false);
    const [notAllow, setNotAllow] = useState(true);

    const navigate = useNavigate();

    const handleAddsignup = () => {
    navigate('/signup');
    };  
    const handleAddFindPW = () => {
      navigate('/findpw');
      };  

    const handleAddFindId = () => {
      navigate('/findid');
      };  
    useEffect(() => {
      if(emailValid && pwValid) {
        setNotAllow(false);
        return;
      }
      setNotAllow(true);
    }, [emailValid, pwValid]);

    const handleEmail = (e) => {
      setEmail(e.target.value);
      const regex =
        /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
      if (regex.test(e.target.value)) {
        setEmailValid(true);
      } else {
        setEmailValid(false);
      }
    };
    const handlePw = (e) => {
      setPw(e.target.value);
      const regex =
        /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/;
      if (regex.test(e.target.value)) {
        setPwValid(true);
      } else {
        setPwValid(false);
      }
    };
    const onClickConfirmButton = () => {
      if(email === User.email && pw === User.pw) {
        alert('로그인에 성공했습니다.')
        navigate('/main');
      } else {
        alert("등록되지 않은 회원입니다.");
      }
    }

    return (
      <div className='page-c'>
      <div className="page-L">
                <img src="logo.png" alt="logo" className="logo-a" />
        <div className="titleWrap">
          이메일과 비밀번호를
          <br />
          입력해주세요
        </div>

        <div className="contentWrap">
          <div className="inputTitle">이메일</div>
          <div className="inputWrap">
          <input
              className="input_text"
              type="email"
              placeholder="이메일"
              value={email}
              onChange={handleEmail}
            />
          </div>
          {/* <div className="errorMessageWrap">
            {!emailValid && email.length > 0 && (
              <div>올바른 이메일을 입력해주세요.</div>
            )}
          </div> */}

          <div style={{ marginTop: "26px" }} className="inputTitle">
            비밀번호
          </div>
          <div className="inputWrap">
            <input
              className="input_text"
              type="password"
              placeholder="비밀번호"
              value={pw}
              onChange={handlePw}
            />
          </div>
          {/* <div className="errorMessageWrap">
            {!pwValid && pw.length > 0 && (
              <div>영문, 숫자, 특수문자 포함 8자 이상 입력해주세요.</div>
            )}
          </div> */}
        </div>

        <div>
          <button onClick={onClickConfirmButton} disabled={notAllow} className="bottomButton">
            로그인
          </button>
        </div>
        <div className="Atag">
            <a onClick={handleAddsignup}>회원가입</a>
            <a onClick={handleAddFindId}>아이디 찾기</a>
            <a onClick={handleAddFindPW}>비밀번호 찾기</a>
        </div>
      </div>
      </div>
    );
}