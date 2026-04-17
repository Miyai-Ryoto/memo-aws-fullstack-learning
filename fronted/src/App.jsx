import React from 'react';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { HomePage } from './pages/HomePage';
import { CreateMemoPage } from './pages/CreateMemoPage';
import { EditMemoPage } from './pages/EditMemoPage';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/memos/new" element={<CreateMemoPage />} />
        <Route path="/memos/new" element={<EditMemoPage />} />
        <Route path="/memos/:id/edit" element={<EditMemoPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
