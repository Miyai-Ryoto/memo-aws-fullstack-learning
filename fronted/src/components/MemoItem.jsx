import { useState } from "react";

export function MemoItem({ memo, onDelete, onUpdate, submitting }) {
  const [editing, setEditing] = useState(false);

  const [title, setTitle] = useState(memo.title ?? "");
  const [content, setContent] = useState(memo.content ?? "");
  const [tags, setTags] = useState((memo.tags ?? []).join(", "));

  const handleDelete = () => {
    if (!onDelete) return;

    if (!window.confirm("このメモを削除しますか？")) return;

    onDelete(memo.id);
  };

  const startEdit = () => {
    // 表示中のmemoから最新値でフォームを初期化（更新後の再編集でズレない）
    setTitle(memo.title ?? "");
    setContent(memo.content ?? "");
    setTags((memo.tags ?? []).join(", "));
    setEditing(true);
  };

  const cancelEdit = () => {
    setEditing(false);
  };

  const saveEdit = async () => {
    if (!onUpdate) return;
    await onUpdate(memo.id, { title, content, tags });
    setEditing(false);
  };

  return (
    <li style={{ marginBottom: 8 }}>
      {!editing ? (
        <>
          <strong>{memo.title}</strong>
          {memo.tags?.length ? <span>（{memo.tags.join(", ")}）</span> : null}

          <div style={{ marginTop: 6, display: "flex", gap: 8 }}>
            <button onClick={startEdit} disabled={submitting}>
              編集
            </button>
            <button onClick={handleDelete} disabled={submitting} style={{ marginLeft: 0 }}>
              削除
            </button>
          </div>
        </>
      ) : (
        <>
          <div>
            <label>
              Title（必須）<br />
              <input
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                disabled={submitting}
                style={{ width: "100%" }}
              />
            </label>
          </div>

          <div style={{ marginTop: 6 }}>
            <label>
              Content<br />
              <textarea
                value={content}
                onChange={(e) => setContent(e.target.value)}
                disabled={submitting}
                style={{ width: "100%", height: 70 }}
              />
            </label>
          </div>

          <div style={{ marginTop: 6 }}>
            <label>
              Tags（カンマ区切り）<br />
              <input
                value={tags}
                onChange={(e) => setTags(e.target.value)}
                disabled={submitting}
                style={{ width: "100%" }}
              />
            </label>
          </div>

          <div style={{ marginTop: 8, display: "flex", gap: 8 }}>
            <button onClick={saveEdit} disabled={submitting}>
              更新
            </button>
            <button onClick={cancelEdit} disabled={submitting}>
              キャンセル
            </button>
          </div>
        </>
      )}
    </li>
  );
}
