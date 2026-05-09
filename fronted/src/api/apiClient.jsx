const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export async function apiRequest(path, options = {}, apiName = "") {
  const res = await fetch(`${BASE_URL}${path}`, {
    headers: {
      "Content-Type": "application/json",
      ...(options.headers || {}),
    },
    ...options,
  });

  if (!res.ok) {
    await handleErrorResponse(res, apiName);
  }

  if (res.status === 204) {
    return null;
  }

  const text = await res.text();

  if (!text) {
    return null;
  }

  return JSON.parse(text);
}

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