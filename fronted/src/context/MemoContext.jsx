import { createContext, useContext, useReducer } from "react";
import { initialState, memoReducer } from "../reducers/memoReducer";

const MemoContext = createContext(null);

export function MemoProvider({ children }) {
  const [state, dispatch] = useReducer(memoReducer, initialState);

  return (
    <MemoContext.Provider value={{ state, dispatch }}>
      {children}
    </MemoContext.Provider>
  );
}

export function useMemoContext() {
  const context = useContext(MemoContext);

  if (!context) {
    throw new Error("useMemoContext must be used within MemoProvider");
  }

  return context;
}