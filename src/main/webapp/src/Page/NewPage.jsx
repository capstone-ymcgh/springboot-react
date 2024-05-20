import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';

function NewPage({ setPages }) {
    const [newPage, setNewPage] = useState('');

    const handleSubmit = () => {
        setPages(prevPages => [...prevPages, newPage]);
        setNewPage('');
    };

    return (
        <div>
            <textarea value={newPage} onChange={e => setNewPage(e.target.value)} />
            <button onClick={handleSubmit}>저장하기</button>
        </div>
    );
}
export default NewPage;
