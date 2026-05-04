import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { MemoForm } from "../components/MemoForm.jsx";
import { getMemoById, updateMemo, deleteMemo } from "../api/memoApi.jsx";
import { useMemos } from "../hooks/useMemo";

export function EditMemoPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { dispatch } = useMemos();

  const [memo, setMemo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);

  const [errorMessage, setErrorMessage] = useState("");
  const [fieldErrors, setFieldErrors] = useState({});

  useEffect(() => {
    let cancelled = false;

    const fetchMemo = async () => {
      setLoading(true);
      setErrorMessage("");
      setFieldErrors({});

      try {
        const data = await getMemoById(id);

        if (!cancelled) {
          setMemo(data);
        }
      } catch (e) {
        if (!cancelled) {
          setErrorMessage(e?.message ?? "メモの取得に失敗しました");
        }
      } finally {
        if (!cancelled) {
          setLoading(false);
        }
      }
    };

    fetchMemo();

    return () => {
      cancelled = true;
    };
  }, [id]);

  const handleUpdate = async ({ title, content, tags }) => {
    setSubmitting(true);
    setErrorMessage("");
    setFieldErrors({});

    try {
      const updatedMemo = await updateMemo(id, { title, content, tags });

      dispatch({
        type: "UPDATE_MEMO",
        payload: updatedMemo,
      });

      navigate("/");
    } catch (e) {
      if (e?.errors) {
        setFieldErrors(e.errors);
        setErrorMessage(e.message ?? "入力内容を確認してください");
      } else {
        setErrorMessage(e?.message ?? "更新に失敗しました");
      }
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async () => {
    setSubmitting(true);
    setErrorMessage("");
    setFieldErrors({});

    try {
      await deleteMemo(id);

      dispatch({
        type: "DELETE_MEMO",
        payload: Number(id),
      });

      navigate("/");
    } catch (e) {
      setErrorMessage(e?.message ?? "削除に失敗しました"); // 新規作成と更新と異なり入力エラーはない想定なので fieldErrors は更新しない
    } finally {
      setSubmitting(false);
    }
  };

  const handleBack = () => {
    navigate("/");
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!memo) {
    return (
      <div>
        <div>メモが見つかりません</div>
        <button type="button" onClick={handleBack}>
          戻る
        </button>
      </div>
    );
  }

  return (
    <div>
      <h1>メモ編集</h1>

      {errorMessage && (
        <div style={{ color: "red" }}>
          {errorMessage}
        </div>
      )}

      <button type="button" onClick={handleBack}>
        戻る
      </button>

      <MemoForm
        onSubmit={handleUpdate}
        submitting={submitting}
        errors={fieldErrors}
        initialValues={{
          title: memo.title ?? "",
          content: memo.content ?? "",
          tags: Array.isArray(memo.tags) ? memo.tags.join(", ") : memo.tags ?? "",
        }}
        extraActions={
          <button type="button" onClick={handleDelete} disabled={submitting}>
            削除
          </button>
        }
      />
    </div>
  );
}