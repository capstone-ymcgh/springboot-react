import React from 'react';
import { useLocation } from 'react-router-dom';
import './recipe.css';
import TopMenu from '../topmenu/topmenu';

const Recipe = () => {
    const location = useLocation();
    const { recipeTitle,recipeIngredients,selectedIndices, selectedImages, selectedTexts } = location.state || {};

    return (
        <div>
            <TopMenu/>
            <div className='recipe-con'>
            {selectedIndices.map((index, i) => {
                    if (i === selectedIndices.length - 1) {
                        return (
                        <div key={index} className='recipe-main'>
                            <img className="recipe-index-img" src={selectedImages[selectedImages.length - 1]} alt={`Recipe ${index + 1}`} />
                        </div>
                        );
                    }
                    return null;
                    })}
                    <div>
                    <div className='recipe-title'>{recipeTitle}</div>
                    <div>재료</div>
                    <div className='recipe-ingredients'>{recipeIngredients}</div>
                    </div>
                    <div className='recipe-box'>
                    {selectedIndices.map((index, i) => (
                        <div key={index} className='recipe-index'>
                            <img className="recipe-index-img"src={selectedImages[i]} alt={`Recipe ${index + 1}`} />
                            <div className='recipe-text'>
                            <div className='text-index'>Step  {index + 1} </div>
                            <p>{selectedTexts[i]}</p>
                            </div>
                        </div>
                    ))}</div>
                    
            </div>    
        </div>
    );
};

export default Recipe;