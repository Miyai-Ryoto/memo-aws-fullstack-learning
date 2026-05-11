import { apiRequest } from "./apiClient";

// メモの一覧リストを取得
export async function getMemos({ title, tag, content, updatedFrom, updatedTo, favoriteOnly, archivedOnly, sort }) {
  const queryParams = new URLSearchParams();
  if (title) queryParams.append("title", title);
  if (tag) queryParams.append("tag", tag);
  if (content) queryParams.append("content", content);
  if (updatedFrom) queryParams.append("updatedFrom", updatedFrom);
  if (updatedTo) queryParams.append("updatedTo", updatedTo);
  if (favoriteOnly) queryParams.append("favoriteOnly", favoriteOnly);
  if (archivedOnly) queryParams.append("archivedOnly", archivedOnly);
  if (sort) queryParams.append("sort", sort);
  const queryString = queryParams.toString();
  const url = queryString ? `/memos?${queryString}` : "/memos";
  return await apiRequest(url, { method: "GET" }, `GET ${url}`);
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