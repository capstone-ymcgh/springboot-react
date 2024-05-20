import React, { useState } from 'react';
import './recipeText.css';

function RecipeText({ index, text: initialText, image: initialImage, onTextChange, onImageChange }) {
    const [image, setImage] = useState(initialImage);
    const [dynamicWidth, setDynamicWidth] = useState('800px');
    const [dynamicHeight, setDynamicHeight] = useState('500px');
    const [text, setText] = useState(initialText);

    const handleImageUpload = (e) => {
        if (e.target.files && e.target.files[0]) {
            const newImage = URL.createObjectURL(e.target.files[0]);
            setImage(newImage);
            onImageChange(index, newImage);
        }
    };

    const removeImage = () => {
        setImage(null);
        onImageChange(index, null);
    };

    const handleTextChange = (e) => {
        setText(e.target.value);
        onTextChange(index, e.target.value);
    };

    return (
        <div className='imgbox'>
            <div className='recipetextindex'>{index + 1} page</div>
            <div className='imgbox2'>
                {!image && (
                <>
                    <input className='imginput' type="file" accept="image/*" onChange={handleImageUpload} />
                    {/* <p className="preview_msg">클릭 혹은 파일을 이곳에 드롭하세요.</p> */}
                </>
                )}
                {image && (
                    <div className='image'>
                        <img src={image} alt="Preview" style={{ width: dynamicWidth, height: dynamicHeight }} />
                        <button className='removeButton' onClick={removeImage}>다시 선택하기</button>
                    </div>
                )}
                <textarea className='textbox' placeholder="여기에 글을 입력하세요" value={text} onChange={handleTextChange} />
            </div>
        </div>
    );
}

export default RecipeText;











