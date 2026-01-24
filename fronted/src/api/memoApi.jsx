const BASE_URL = "http://localhost:8080";

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

/**
 * Day9用（今は未使用でもOK）
 */
export async function createMemo(payload) {
  const res = await fetch(`${BASE_URL}/memos`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (!res.ok) {
    // Spring Validation の 400 を拾えるようにしておく
    const text = await res.text().catch(() => "");
    throw new Error(`POST /memos failed: ${res.status} ${text}`);
  }

  return await res.json();
}
