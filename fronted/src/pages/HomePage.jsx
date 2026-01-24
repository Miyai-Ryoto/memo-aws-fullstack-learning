import { useEffect, useReducer } from "react";
import { initialState, memoReducer } from "../reducers/memoReducer";
import { MemoList } from "../components/MemoList.jsx";
import { getMemos } from "../api/memoApi.jsx";

export function HomePage() {
  const [state, dispatch] = useReducer(memoReducer, initialState);

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

  if (state.status === "loading") return <div>Loading...</div>;
  if (state.status === "error") return <div>Error: {state.error}</div>;

  return (
    <div>
      <h1>MemoApp</h1>
      <MemoList memos={state.memos} />
    </div>
  );
}
