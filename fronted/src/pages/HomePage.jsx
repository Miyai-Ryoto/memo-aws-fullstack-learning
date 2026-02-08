import { useEffect, useReducer, useState } from "react";
import { initialState, memoReducer } from "../reducers/memoReducer";
import { MemoList } from "../components/MemoList.jsx";
import { MemoForm } from "../components/MemoForm.jsx";
import { getMemos, createMemo, deleteMemo, updateMemo } from "../api/memoApi.jsx";

export function HomePage() {
  const [state, dispatch] = useReducer(memoReducer, initialState);
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    let cancelled = false;

    (async () => {
      dispatch({ type: "FETCH_START" });

      try {
        const memos = await getMemos();
        if (!cancelled) {
          dispatch({ type: "FETCH_SUCCESS", payload: memos });
        }
      } catch (e) {
        if (!cancelled) {
          dispatch({
            type: "FETCH_ERROR",
            payload: e instanceof Error ? e.message : String(e),
          });
        }
      }
    })();

    return () => {
      cancelled = true;
    };
  }, []);

  const handleCreate = async ({ title, content, tags }) => {
    setSubmitting(true);
    try {
      const created = await createMemo({ title, content, tags });
      dispatch({ type: "ADD_MEMO", payload: created });
    } catch (e) {
      dispatch({
        type: "FETCH_ERROR",
        payload: e instanceof Error ? e.message : String(e),
      });
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async (id) => {
    setSubmitting(true);
    try {
      await deleteMemo(id);
      dispatch({ type: "DELETE_MEMO", payload: id });
    } catch (e) {
      dispatch({
        type: "FETCH_ERROR",
        payload: e instanceof Error ? e.message : String(e),
      });
    } finally {
      setSubmitting(false);
    }
  };

  const handleUpdate = async (id, { title, content, tags }) => {
    setSubmitting(true);
    try {
      const updated = await updateMemo(id, { title, content, tags });
      dispatch({ type: "UPDATE_MEMO", payload: updated });
    } catch (e) {
      dispatch({
        type: "FETCH_ERROR",
        payload: e instanceof Error ? e.message : String(e),
      });
    } finally {
      setSubmitting(false);
    }
  };


  return (
    <div>
      <h1>MemoApp</h1>

      <MemoForm onSubmit={handleCreate} submitting={submitting} />

      {state.status === "loading" ? <div>Loading...</div> : null}
      {state.status === "error" ? <div>Error: {state.error}</div> : null}

      <MemoList memos={state.memos} onDelete={handleDelete} onUpdate={handleUpdate} submitting={submitting} />
    </div>
  );
}
