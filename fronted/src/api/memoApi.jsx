const BASE_URL = import.meta.env.VITE_API_BASE_URL;

// メモの一覧リストを取得
export async function getMemos() {
  const res = await fetch(`${BASE_URL}/memos`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });

  if (!res.ok) {
    throw new Error(`GET /memos failed: ${res.status}`);
  }

  return await res.json();
}

// メモの詳細を取得
export async function getMemoById(id) {
  const res = await fetch(`${BASE_URL}/memos/${id}`, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });

  if (!res.ok) {
    throw new Error(`GET /memos/${id} failed: ${res.status}`);
  }

  return await res.json();
}

// メモの新規作成
export async function createMemo({ title, content, tags }) {
  const res = await fetch(`${BASE_URL}/memos`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, content, tags }),
  });

  if (!res.ok) {
    await handleErrorResponse(res, "POST /memos");
  }

  return await res.json();
}

// メモの更新
export async function updateMemo(id, { title, content, tags }) {
  const res = await fetch(`${BASE_URL}/memos/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, content, tags }),
  });

  if (!res.ok) {
    await handleErrorResponse(res, `PUT /memos/${id}`);
  }

  return await res.json(); 
}

// メモの削除
export async function deleteMemo(id) {
  const res = await fetch(`${BASE_URL}/memos/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    await handleErrorResponse(res, `DELETE /memos/${id}`);
  }
}

// APIエラーのレスポンスを処理する共通関数
async function handleErrorResponse(res, apiName) {
  let errorBody = null;

  try {
    errorBody = await res.json();
  } catch {
    errorBody = {
      status: res.status,
      message: `${apiName} failed`,
    };
  }

  throw {
    status: errorBody.status ?? res.status,
    message: errorBody.message ?? `${apiName} failed`,
    errors: errorBody.errors ?? {},
  };
}

