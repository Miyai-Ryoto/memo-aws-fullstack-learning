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
  const [error, setError] = useState("");

  useEffect(() => {
    let cancelled = false;

    const fetchMemo = async () => {
      setLoading(true);
      setError("");

      try {
        const data = await getMemoById(id);

        if (!cancelled) {
          setMemo(data);
        }
      } catch (e) {
        if (!cancelled) {
          setError(e instanceof Error ? e.message : String(e));
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
    setError("");

    try {
      const updatedMemo = await updateMemo(id, { title, content, tags });

      dispatch({
        type: "UPDATE_MEMO",
        payload: updatedMemo,
      });

      navigate("/");
    } catch (e) {
      setError(e instanceof Error ? e.message : String(e));
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async () => {
    setSubmitting(true);
    setError("");

    try {
      await deleteMemo(id);

      dispatch({
        type: "DELETE_MEMO",
        payload: Number(id),
      });

      navigate("/");
    } catch (e) {
      setError(e instanceof Error ? e.message : String(e));
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

  if (error) {
    return (
      <div>
        <div>Error: {error}</div>
        <button type="button" onClick={handleBack}>
          戻る
        </button>
      </div>
    );
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

      <button type="button" onClick={handleBack}>
        戻る
      </button>

      <MemoForm
        onSubmit={handleUpdate}
        submitting={submitting}
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