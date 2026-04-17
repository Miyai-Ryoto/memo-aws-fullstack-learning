import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MemoForm } from "../components/MemoForm.jsx";
import { createMemo } from "../api/memoApi.jsx";

export function CreateMemoPage() {
  const [submitting, setSubmitting] = useState(false);
  const navigate = useNavigate();

  const handleCreate = async ({ title, content, tags }) => {
    setSubmitting(true);
    try {
      await createMemo({ title, content, tags });
      navigate("/"); // 登録後は一覧へ戻る
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div>
      <h1>メモ登録</h1>

      <MemoForm 
        onSubmit={handleCreate} 
        submitting={submitting}
        initialValues={{
          title: "",
          content: "",
          tags: "",
        }} 
      />
    </div>
  );
}