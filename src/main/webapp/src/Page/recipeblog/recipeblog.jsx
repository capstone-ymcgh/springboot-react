import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './recipeblog.css';
import TopMenu from '../topmenu/topmenu';

const RecipeBlog = () => {
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 상태
    const itemsPerPage = 20; // 페이지 당 아이템 수

    const navigate = useNavigate(); // 페이지 이동 함수

    const handleAddRecipe = () => {
        navigate('/recipePage'); // 레시피 추가 페이지로 이동
    };  

    const recipes = Array(500).fill().map((_, i) => ({ // 레시피 데이터 생성
        title: `Recipe ${i + 1}`,
        description: '설명',
        imageUrl: '/header_img.png'
    }));

    const [searchTerm, setSearchTerm] = useState(''); // 검색어 상태

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value); // 검색어 변경 핸들러
    };

    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber); // 페이지 변경 핸들러
    };

    const startIndex = (currentPage - 1) * itemsPerPage; // 현재 페이지의 시작 인덱스
    const selectedRecipes = recipes.slice(startIndex, startIndex + itemsPerPage); // 현재 페이지의 레시피 선택

    const totalPages = Math.ceil(recipes.length / itemsPerPage); // 총 페이지 수

    const handleNextPage = () => {
        if (currentPage < totalPages) {
            setCurrentPage(currentPage + 1); // 다음 페이지로 이동
        }
    };
    
    const handlePreviousPage = () => {
        if (currentPage > 1) {
            setCurrentPage(currentPage - 1); // 이전 페이지로 이동
        }
    };

    const [startPageState, setStartPage] = useState(1); // 시작 페이지 상태

    const handleNextSet = () => {
        if (startPageState + 10 <= totalPages) {
            setStartPage(startPageState + 10); // 다음 페이지 세트로 이동
        }
    };
    
    const handlePreviousSet = () => {
        if (startPageState - 10 >= 1) {
            setStartPage(startPageState - 10); // 이전 페이지 세트로 이동
        }
    };
    
    const startPage = Math.floor((currentPage - 1) / 10) * 10 + 1; // 시작 페이지 계산
    const endPage = Math.min(startPage + 9, totalPages); // 끝 페이지 계산
    
    const pageNumbers = [];
    for (let i = startPage; i <= endPage; i++) {
        pageNumbers.push(i); // 페이지 번호 배열 생성
    }

    const [sortType, setSortType] = useState('recommendations'); // 정렬 타입 상태 ('recommendations' 또는 'latest')

    const handleSortChange = (newSortType) => {
        setSortType(newSortType); // 정렬 타입 변경 핸들러
    };

    const sortedRecipes = [...selectedRecipes].sort((a, b) => {
        if (sortType === 'recommendations') {
            return b.recommendations - a.recommendations; // 추천 수가 많은 순서로 정렬
        } else if (sortType === 'latest') {
            return new Date(b.date) - new Date(a.date); // 최신 날짜 순서로 정렬
        } else {
            return 0;
        }
    });

    return (
        <div>
            <TopMenu />
            <div className='recipePage'>레시피 게시판</div>
            <div className='recipe-search'>
                <input className="search-text"type="text" value={searchTerm} onChange={handleSearchChange} placeholder="" />
                <button className='button-Catagory'>
                </button>
            </div>
            {/* 검색결과 있으면 검색 없으면 없다는 글 보여줌 */}
            <div className='search-resualt'></div>
            
            <div className="grid-container">
                <div className='click-button'>
                <button className="button-d"onClick={() => handleSortChange('recommendations')}>추천순</button>
                <button className="button-d"onClick={() => handleSortChange('latest')}>최신순</button>
                </div>
                <div className="recipe-grid">
                    {sortedRecipes.map((recipe, index) => (
                        <div key={index} className="recipe-item">
                            <img className='recipe-img' src={recipe.imageUrl} alt={recipe.title} />
                            <h3>{recipe.title}</h3>
                            <p>{recipe.description}</p>
                        </div>
                    ))}
                </div>
            </div>
            <div>
                <button className="recipe-add" onClick={handleAddRecipe}>나만의 레시피 추가하기</button>
            </div>
            <div className='recipePage-bottom'>
                <button className="recipePage-bottom-button" onClick={handlePreviousPage}>&lt;</button>
                {pageNumbers.map((page) => (
                    <button className="recipePage-bottom-button" key={page} onClick={() => handlePageChange(page)}>{page}</button>
                ))}
                <button className="recipePage-bottom-button" onClick={handleNextPage}>&gt;</button>
            </div>
        </div>
    );
};

export default RecipeBlog;