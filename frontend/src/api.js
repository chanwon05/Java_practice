export async function request(path, options = {}) {
  const response = await fetch(path, {
    ...options,
    headers: { 'Content-Type': 'application/json', ...options.headers },
  });
  if (!response.ok) {
    const error = await response.json().catch(() => ({}));
    throw new Error(error.message || '요청을 처리하지 못했습니다. 다시 시도해주세요.');
  }
  return response.status === 204 ? null : response.json();
}
