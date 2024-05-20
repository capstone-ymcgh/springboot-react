// import './Board.css'; // CSS 파일을 import
// import TopMenu from '.topmenu/topmenu';
// import React, { useState, useEffect } from 'react';
// import { useNavigate } from 'react-router-dom';

// // Board 컴포넌트 정의

// const Board = () => {
//     // recipes 상태와 그 상태를 변경하는 함수를 정의
//     const [recipes, setRecipes] = useState([]);
//     // 라우팅을 위한 navigate 함수를 가져옴
//     const navigate = useNavigate();

//     // 페이지가 로드될 때 API에서 레시피 데이터를 가져오는 코드
//     // 현재는 주석 처리되어 있음
//     // useEffect(() => {
//     //     fetch('API 주소')
//     //         .then(response => response.json())
//     //         .then(data => setRecipes(data))
//     //         .catch(error => console.error(error));
//     // }, []);

//     // 좋아요 버튼을 클릭했을 때의 처리 함수
//     const handleLike = (index) => {
//         const newRecipes = [...recipes];
//         newRecipes[index].likes = (newRecipes[index].likes || 0) + 1;
//         setRecipes(newRecipes);
//     };

//     // 댓글 버튼을 클릭했을 때의 처리 함수
//     const handleComment = (index) => {
//         const newRecipes = [...recipes];
//         newRecipes[index].comments = (newRecipes[index].comments || 0) + 1;
//         setRecipes(newRecipes);
//     };

//     // 레시피 추가 버튼을 클릭했을 때의 처리 함수
//     const handleAddRecipe = () => {
//         // '/add-recipe' 경로로 이동
//         navigate('/add-recipe');
//     };

//     // 컴포넌트가 렌더링하는 JSX
//     return (
//         <div>
//             <TopMenu />
//             <h1>레시피 게시판</h1>
//             <button onClick={handleAddRecipe}>내 레시피 추가하기</button>
//             {recipes.map((recipe, index) => (
//                 <div key={index}>
//                     <h2>{recipe.title}</h2>
//                     <p>{recipe.description}</p>
//                     <button onClick={() => handleLike(index)}>좋아요 ({recipe.likes || 0})</button>
//                     <button onClick={() => handleComment(index)}>댓글 수 ({recipe.comments || 0})</button>
//                 </div>
//             ))}
//         </div>
//     );
// };

// // Board 컴포넌트를 export
// export default Board;