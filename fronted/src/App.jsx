import { BrowserRouter, Routes, Route } from "react-router-dom";
import { MemoProvider } from "./context/MemoContext";
import { HomePage } from './pages/HomePage';
import { CreateMemoPage } from './pages/CreateMemoPage';
import { EditMemoPage } from './pages/EditMemoPage';

function App() {
  return (
    <BrowserRouter>
      <MemoProvider>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/memos/new" element={<CreateMemoPage />} />
          <Route path="/memos/:id/edit" element={<EditMemoPage />} />
        </Routes>
      </MemoProvider>
    </BrowserRouter>
  );
}

export default App;
