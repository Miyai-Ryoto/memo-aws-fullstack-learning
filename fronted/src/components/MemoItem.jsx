export function MemoItem({ memo, onEdit }) {

  const handleEdit = () => {
    if (!onEdit) return;
    onEdit(memo.id);
  };

  return (
    <li style={{ marginBottom: 8 }}>
      <strong>{memo.title}</strong>
      {memo.tags?.length ? <span>（{memo.tags.join(", ")}）</span> : null}

      <div style={{ marginTop: 6, display: "flex", gap: 8 }}>
        <button onClick={handleEdit}>
          編集
        </button>
      </div>
    </li>
  );
}
