import { useEffect, useReducer } from "react";
import { initialState, memoReducer } from "../reducers/memoReducer";
import { MemoList } from "../components/MemoList.jsx";
import { getMemos} from "../api/memoApi.jsx";
import { useNavigate } from "react-router-dom"

export function HomePage() {
  const [state, dispatch] = useReducer(memoReducer, initialState);
  const navigate = useNavigate();

  const handleMoveToCreatePage = () => {
    navigate("/memos/new");
  };

  const handleMoveToEditPage = (id) => {
    navigate(`/memos/${id}/edit`);
  };

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

  return (
    <div>
      <h1>MemoApp</h1>

      <button onClick={handleMoveToCreatePage}>
        新規登録
      </button>

      {state.status === "loading" ? <div>Loading...</div> : null}
      {state.status === "error" ? <div>Error: {state.error}</div> : null}

      <MemoList
        memos={state.memos}
        onEdit={handleMoveToEditPage}
      />
    </div>
  );
}
