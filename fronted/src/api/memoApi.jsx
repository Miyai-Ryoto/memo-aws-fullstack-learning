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

export async function createMemo({ title, content, tags }) {
  const res = await fetch(`${BASE_URL}/memos`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, content, tags }),
  });

  if (!res.ok) {
    // Validation(400) などの情報を拾う
    const text = await res.text().catch(() => "");
    throw new Error(`POST /memos failed: ${res.status} ${text}`.trim());
  }

  return await res.json();
}

