import React, { useState } from 'react';
import TopMenu from '../topmenu/topmenu';
import './UserInfo.css';

const UserInfo = () => {
  const [nickname, setNickname] = useState('');
  const [image, setImage] = useState('https://example.com/user/current/profile/image.jpg'); // 사용자의 현재 프로필 이미지 URL
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [accountSettings, setAccountSettings] = useState({ notifications: false, language: 'English', theme: 'Light' });

  const handleNicknameChange = (e) => {
    setNickname(e.target.value);
  };

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    const reader = new FileReader();

    reader.onloadend = () => {
      setImage(reader.result);
    };

    if (file) {
      reader.readAsDataURL(file);
    } else {
      setImage('');
    }
  };

  const handlePasswordChange = (e) => {
    setPassword(e.target.value);
  };

  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handleAccountSettingsChange = (e) => {
    setAccountSettings({
      ...accountSettings,
      [e.target.name]: e.target.value
    });
  };

  const handleLogout = () => {
    // 로그아웃 로직을 여기에 추가하세요.
  };

  const handleDeleteAccount = () => {
    // 계정 삭제 로직을 여기에 추가하세요.
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // 사용자 정보 변경 로직을 여기에 추가하세요.
  };

  return (
    <div>
      <TopMenu/>
      <div className='user-info-con'>
        <form onSubmit={handleSubmit}>
          <div className='user-info-main'>
            <div className="user-img-container">
              <img className="user-img" src={image} alt="프로필 이미지" />
            </div>
            <label>
              내 이미지 변경하기:
              <input type="file" onChange={handleImageChange} required />
            </label>
            <label>
              닉네임 변경하기:
              <input className="user-text"type="text" value={nickname} onChange={handleNicknameChange} required />
            </label>
            <label>
              이메일 주소 변경하기:
              <input className="user-text"type="email" value={email} onChange={handleEmailChange} required />
            </label>
            <label>
              비밀번호 변경하기:
              <input className="user-text"type="password" value={password} onChange={handlePasswordChange} required />
            </label>
            <label>
              언어 설정:
              <select name="language" value={accountSettings.language} onChange={handleAccountSettingsChange}>
                <option value="English">English</option>
                <option value="Korean">Korean</option>
                {/* 필요한 언어를 추가하세요 */}
              </select>
            </label>
            <button type="submit">변경하기</button>
            <button type="button" onClick={handleLogout}>로그아웃</button>
            <button type="button" onClick={handleDeleteAccount}>계정 삭제</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default UserInfo;