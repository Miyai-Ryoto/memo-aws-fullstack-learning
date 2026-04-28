import { useMemoContext } from "../context/MemoContext";

export function useMemos() {
  return useMemoContext();
}