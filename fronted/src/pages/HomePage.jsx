import { useCallback, useEffect } from "react";
import { MemoList } from "../components/MemoList.jsx";
import { getMemos } from "../api/memoApi.jsx";
import { useNavigate } from "react-router-dom";
import { useMemos } from "../hooks/useMemo";
import { useMemoFilter } from "../hooks/useMemoFilter";

export function HomePage() {
  const { state, dispatch } = useMemos();
  const navigate = useNavigate();
  const { keyword, setKeyword, filteredMemos } = useMemoFilter(state.memos);

  const handleMoveToCreatePage = () => {
    navigate("/memos/new");
  };

  const handleMoveToEditPage = (id) => {
    navigate(`/memos/${id}/edit`);
  };

  const fetchMemos = useCallback(async () => {
    dispatch({ type: "FETCH_START" }); 
    
    try {
      const memos = await getMemos();
      dispatch({ type: "FETCH_SUCCESS", payload: memos });
    } catch (e) {
      dispatch({
        type: "FETCH_ERROR",
        payload: e?.message ?? "メモの取得に失敗しました",
      });
    }
  }, [dispatch]);

   // 初回取得
  useEffect(() => {
    fetchMemos();
  }, [fetchMemos]);

  // 🔥 SSE追加
  useEffect(() => {
    const eventSource = new EventSource("http://localhost:8080/sse/memos");

    eventSource.addEventListener("memo-changed", async () => {
      console.log("SSE受信 → 再取得");
      await fetchMemos();
    });

    eventSource.onerror = (err) => {
      console.error("SSEエラー", err);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, [fetchMemos]);


  return (
    <div>
      <h1>MemoApp</h1>

      <input
        type="text"
        placeholder="タグで絞り込み"
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
      />
      <button onClick={handleMoveToCreatePage}>
        新規登録
      </button>

      {state.status === "loading" ? <div>Loading...</div> : null}
      {state.status === "error" ? <div>Error: {state.error}</div> : null}

      <MemoList
        memos={filteredMemos}
        onEdit={handleMoveToEditPage}
      />
    </div>
  );
}