import { MemoItem } from "./MemoItem.jsx";

export function MemoList({ memos, onEdit }) {
  if (!memos || memos.length === 0) return <div>メモがありません</div>;

  return (
    <ul>
      {memos.map((memo) => (
        <MemoItem
          memo={memo}
          onEdit={onEdit}
        />
      ))}
    </ul>
  );
}
