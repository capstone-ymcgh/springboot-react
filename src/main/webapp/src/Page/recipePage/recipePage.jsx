import React, { useState, useEffect } from 'react';
import TopMenu from '../topmenu/topmenu';
import './recipePage.css';
import RecipeText from './recipeText';
import { useNavigate } from 'react-router-dom';

function RecipePage() {
    const [recipeTexts, setRecipeTexts] = useState([{ key: 0, text: '', image: null }]);
    const [activeIndex, setActiveIndex] = useState(0);
    const [selectedIndices, setSelectedIndices] = useState([]);
    const [selectedImages, setSelectedImages] = useState([]);
    const [selectedTexts, setSelectedTexts] = useState([]);
    const [recipeBlog, setRecipeBlog] = useState([]);
    const [recipeTitle, setRecipeTitle] = useState(''); 
    const [recipeIngredients, setRecipeIngredients] = useState(''); 
    const navigate = useNavigate();

    const addRecipeText = () => {
        setRecipeTexts([...recipeTexts, { key: recipeTexts.length, text: '', image: null }]);
        setActiveIndex(recipeTexts.length);
    };

    const afterRecipeText = () => {
        if (activeIndex < recipeTexts.length - 1) {
            setActiveIndex(activeIndex + 1);
        }
    }

    const beforeRecipeText = () => {
        if (activeIndex > 0) {
            setActiveIndex(activeIndex - 1);
        }
    };

    const handleTextChange = (index, text) => {
        const newRecipeTexts = [...recipeTexts];
        newRecipeTexts[index].text = text;
        setRecipeTexts(newRecipeTexts);
    };

    const handleImageChange = (index, image) => {
        const newRecipeTexts = [...recipeTexts];
        newRecipeTexts[index].image = image;
        setRecipeTexts(newRecipeTexts);
    };
    const handleTitleChange = (event) => {
        setRecipeTitle(event.target.value);
    };
    const handleIngredientsChange = (event) => {
        setRecipeIngredients(event.target.value);
    };

    const handleSave = () => {
        setRecipeBlog([...recipeBlog, recipeTexts]);
        recipeTexts.forEach((item, index) => {
            setSelectedIndices(prevIndices => [...prevIndices, index]);
            setSelectedImages(prevImages => [...prevImages, item.image]);
            setSelectedTexts(prevTexts => [...prevTexts, item.text]);
        });
    };

    useEffect(() => {
        if (selectedIndices.length > 0 && selectedImages.length > 0 && selectedTexts.length > 0) {
            navigate('/recipe', { state: { recipeTitle,recipeIngredients, selectedIndices, selectedImages, selectedTexts } });
        }
    }, [recipeTitle,recipeIngredients, selectedIndices, selectedImages, selectedTexts]);

    return (
        <div>
            <TopMenu />
            <button className="left-button" onClick={beforeRecipeText}>
                <img src='/free-icon-font-angle-left-3916934.png' alt="button image" />
            </button>
            {activeIndex >= recipeTexts.length - 1 ? (
                <button className="add-button" onClick={addRecipeText}>
                    <img src='/free-icon-add-6998878.png' alt="button image" />
                </button>
            ) : (
                <button className="right-button" onClick={afterRecipeText}>
                    <img src='/free-icon-font-angle-right-3916924.png' alt="button image" />
                </button>
            )}
            <div className='recipename'>
                <div>
                <input className="recipename-t"type="text" placeholder="레시피 제목을 입력하세요"value={recipeTitle} onChange={handleTitleChange} />
                </div>
                <div>
                <input className="recipeingredients-t"type="text" placeholder="재료를 입력해주세요"value={recipeIngredients} onChange={handleIngredientsChange} />
                </div>
            </div>
            <RecipeText
                key={recipeTexts[activeIndex].key}
                index={activeIndex}
                text={recipeTexts[activeIndex].text}
                image={recipeTexts[activeIndex].image}
                onTextChange={handleTextChange}
                onImageChange={handleImageChange}
            />
            <div>마지막장에는 완성 사진을 올려주세요</div>
            <button className="save" onClick={handleSave}>Save</button>
            
        </div>
    );
}

export default RecipePage;