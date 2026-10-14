import React, { useEffect, useState } from 'react';
import { request } from './api.js';

const filters = { all: '전체', active: '미완료', completed: '완료' };

export default function App() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState('');
  const [filter, setFilter] = useState('all');
  const [loading, setLoading] = useState(true);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState('');

  async function loadTodos() {
    setLoading(true);
    setError('');
    try {
      setTodos(await request('/todos'));
    } catch (error) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { loadTodos(); }, []);

  async function mutate(action) {
    setBusy(true);
    setError('');
    try {
      await action();
    } catch (error) {
      setError(error.message);
    } finally {
      setBusy(false);
    }
  }

  function addTodo(event) {
    event.preventDefault();
    if (!title.trim()) {
      setError('할 일을 입력해주세요.');
      return;
    }
    mutate(async () => {
      const saved = await request('/todos', {
        method: 'POST', body: JSON.stringify({ title: title.trim() }),
      });
      setTodos(current => [...current, saved]);
      setTitle('');
      setFilter('all');
    });
  }

  function toggleTodo(todo) {
    mutate(async () => {
      const saved = await request(`/todos/${todo.id}/completed`, {
        method: 'PATCH', body: JSON.stringify({ completed: !todo.completed }),
      });
      setTodos(current => current.map(item => item.id === saved.id ? saved : item));
    });
  }

  function deleteTodo(todo) {
    mutate(async () => {
      await request(`/todos/${todo.id}`, { method: 'DELETE' });
      setTodos(current => current.filter(item => item.id !== todo.id));
    });
  }

  const remaining = todos.filter(todo => !todo.completed).length;
  const visibleTodos = todos.filter(todo =>
    filter === 'all' || (filter === 'completed' ? todo.completed : !todo.completed)
  );

  return (
    <main className="todo-app">
      <header>
        <p className="eyebrow">하나씩, 차근차근</p>
        <h1>나의 할 일<span className="title-dot">.</span></h1>
        <p className="subtitle">오늘도 하나씩 해보자!.</p>
      </header>

      <section className="todo-card" aria-label="할 일 관리">
        <form className="add-form" onSubmit={addTodo}>
          <label htmlFor="todo-title">새로운 할 일</label>
          <div className="input-row">
            <input id="todo-title" value={title} onChange={event => setTitle(event.target.value)}
              placeholder="어떤 일을 해볼까요?" maxLength={20} required disabled={busy || loading}
              aria-describedby="title-hint" autoComplete="off" />
            <button className="add-button" disabled={busy || loading} type="submit">추가</button>
          </div>
          <p className="input-hint" id="title-hint">20자 이내로 입력해주세요.</p>
        </form>

        {error && <div className="error" role="alert">
          <span>{error}</span>
          <button type="button" onClick={loadTodos} disabled={busy || loading}>목록 다시 읽기</button>
        </div>}

        <div className="list-toolbar">
          <div className="filters" role="group" aria-label="완료 여부 필터">
            {Object.entries(filters).map(([key, label]) => (
              <button key={key} type="button" aria-pressed={filter === key}
                onClick={() => setFilter(key)}>{label}</button>
            ))}
          </div>
          <span className="count" aria-live="polite">{remaining}개 남음</span>
        </div>

        {loading ? <p className="empty" role="status">할 일을 불러오는 중이에요…</p>
          : visibleTodos.length === 0 ? <p className="empty">
            {todos.length === 0 ? '첫 번째 할 일을 추가해보세요.' : '이 목록에는 아직 할 일이 없어요.'}
          </p> : (
            <ul className="todo-list">
              {visibleTodos.map(todo => (
                <li key={todo.id} className={todo.completed ? 'todo-item completed' : 'todo-item'}>
                  <label className="todo-label">
                    <input type="checkbox" checked={todo.completed} disabled={busy}
                      onChange={() => toggleTodo(todo)} />
                    <span>{todo.title}</span>
                  </label>
                  <button className="delete-button" type="button" disabled={busy}
                    aria-label={`${todo.title} 삭제`} onClick={() => deleteTodo(todo)}>삭제</button>
                </li>
              ))}
            </ul>
          )}
        <footer className="card-footer">작은 완료가 모여, 오늘을 채워요.</footer>
      </section>
      <p className="page-note">할 일은 서버에 저장됩니다.</p>
    </main>
  );
}
