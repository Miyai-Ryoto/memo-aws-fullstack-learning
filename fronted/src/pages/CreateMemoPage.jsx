import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { MemoForm } from "../components/MemoForm.jsx";
import { createMemo } from "../api/memoApi.jsx";
import { useMemos } from "../hooks/useMemo";

export function CreateMemoPage() {
  const [submitting, setSubmitting] = useState(false);
  const navigate = useNavigate();
  const { dispatch } = useMemos();
  const [errorMessage, setErrorMessage] = useState("");
  const [fieldErrors, setFieldErrors] = useState({});

  const handleCreate = async ({ title, content, tags }) => {
    setSubmitting(true);
    setErrorMessage("");
    setFieldErrors({});

    try {
      const createdMemo = await createMemo({ title, content, tags });

      dispatch({
        type: "ADD_MEMO",
        payload: createdMemo,
      });

      navigate("/");
    } catch (e) {
      if (e?.errors) {
        setFieldErrors(e.errors);
        setErrorMessage(e.message ?? "入力内容を確認してください");
      } else {
        setErrorMessage(e?.message ?? "登録に失敗しました");
      }
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div>
      <h1>メモ登録</h1>

      {errorMessage && (
        <div style={{ color: "red" }}>
          {errorMessage}
        </div>
      )}

      <MemoForm 
        onSubmit={handleCreate} 
        submitting={submitting}
        errors={fieldErrors}
        initialValues={{
          title: "",
          content: "",
          tags: "",
        }} 
      />
    </div>
  );
}