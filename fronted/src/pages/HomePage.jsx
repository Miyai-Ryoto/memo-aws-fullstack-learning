import { useEffect, useReducer } from "react";
import { initialState, memoReducer } from "../reducers/memoReducer";
import { MemoList } from "../components/MemoList.jsx";

export function HomePage() {
  const [state, dispatch] = useReducer(memoReducer, initialState);

  useEffect(() => {
    // Day3は“動作確認用”のダミーでOK
    dispatch({ type: "FETCH_START" });

    const dummy = [
      { id: 1, title: "メモ1", content: "内容", tags: ["aws"] },
      { id: 2, title: "メモ2", content: "内容", tags: ["react"] },
    ];

    dispatch({ type: "FETCH_SUCCESS", payload: dummy });
  }, []);

  if (state.status === "loading") return <div>Loading...</div>;
  if (state.status === "error") return <div>Error: {state.error}</div>;

  return (
    <div>
      <h1>MemoApp</h1>
      <MemoList memos={state.memos} />
    </div>
  );
}
