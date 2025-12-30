export function MemoItem({ memo }) {
  return (
    <li>
      <strong>{memo.title}</strong>
      {memo.tags?.length ? <span>（{memo.tags.join(", ")}）</span> : null}
    </li>
  );
}
