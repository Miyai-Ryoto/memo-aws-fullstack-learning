import { useState } from "react";

export function MemoForm({ onSubmit, submitting }) {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [tags, setTags] = useState(""); // "a,b,c" 形式

  const handleSubmit = async (e) => {
    e.preventDefault();
    await onSubmit({ title, content, tags });

    // 成功したらクリア
    setTitle("");
    setContent("");
    setTags("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>title *</label>
        <input
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          disabled={submitting}
        />
      </div>

      <div>
        <label>content</label>
        <textarea
          value={content}
          onChange={(e) => setContent(e.target.value)}
          disabled={submitting}
        />
      </div>

      <div>
        <label>tags（カンマ区切り）</label>
        <input
          value={tags}
          onChange={(e) => setTags(e.target.value)}
          disabled={submitting}
          placeholder="react,api"
        />
      </div>

      <button type="submit" disabled={submitting}>
        {submitting ? "Saving..." : "Add Memo"}
      </button>
    </form>
  );
}
