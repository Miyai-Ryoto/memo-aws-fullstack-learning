import { MemoItem } from "./MemoItem.jsx";

export function MemoList({ memos, onDelete, onUpdate, submitting }) {
  if (!memos || memos.length === 0) return <div>メモがありません</div>;

  return (
    <ul>
      {memos.map((memo) => (
        <MemoItem
          key={memo.id}
          memo={memo}
          onDelete={onDelete}
          onUpdate={onUpdate}
          submitting={submitting}
        />
      ))}
    </ul>
  );
}
