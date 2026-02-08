export const initialState = {
  memos: [],
  status: "idle", // "idle" | "loading" | "success" | "error"
  error: null,
};

export function memoReducer(state, action) {
  switch (action.type) {
    case "FETCH_START":
      return { ...state, status: "loading", error: null };

    case "FETCH_SUCCESS":
      return { ...state, status: "success", memos: action.payload, error: null };

    case "FETCH_ERROR":
      return { ...state, status: "error", error: action.payload };

    case "ADD_MEMO":
      return { ...state, memos: [action.payload, ...state.memos] };
    
    case "DELETE_MEMO":
      return { ...state, memos: state.memos.filter((m) => m.id !== action.payload),};
    
    case "UPDATE_MEMO": {
      const updated = action.payload;
      return { ...state, memos: state.memos.map((m) => (m.id === updated.id ? updated : m)),};
    }

    default:
      return state;
  }
}
