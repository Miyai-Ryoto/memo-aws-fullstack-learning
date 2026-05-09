import { apiRequest } from "./apiClient";

// メモの一覧リストを取得
export async function getMemos() {
  return await apiRequest("/memos", { method: "GET" }, "GET /memos");
}

// メモの詳細を取得
export async function getMemoById(id) {
  return await apiRequest(`/memos/${id}`, { method: "GET" }, `GET /memos/${id}`);
}

// メモの新規作成
export async function createMemo({ title, content, tags }) {
  return await apiRequest(
    "/memos",
    {
      method: "POST",
      body: JSON.stringify({ title, content, tags }),
    },
    "POST /memos"
  );
}

// メモの更新
export async function updateMemo(id, { title, content, tags }) {
  return await apiRequest(
    `/memos/${id}`,
    {
      method: "PUT",
      body: JSON.stringify({ title, content, tags }),
    },
    `PUT /memos/${id}`
  );
}

// メモの削除
export async function deleteMemo(id) {
  return await apiRequest(
    `/memos/${id}`,
    {
      method: "DELETE",
    },
    `DELETE /memos/${id}`
  );
}