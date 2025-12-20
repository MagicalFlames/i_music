import { useState } from 'react'
import { BASE_URL } from '../config'
import LoadingOverlay from './LoadingOverlay'
import { showToast } from './Toast'
import './Login.css'

function Login({ onClose, onLoginSuccess }) {
  const [mode, setMode] = useState('login') // 'login', 'register'
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [isLoading, setIsLoading] = useState(false)

  // 账号密码登录
  const handlePasswordLogin = async (e) => {
    e.preventDefault()
    setError('')
    setIsLoading(true)

    try {
      const response = await fetch(`${BASE_URL}/api/user/login/password`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
        credentials: 'include'
      })

      const data = await response.json()

      if (data.success) {
        // 保存到 LocalStorage
        localStorage.setItem('username', username)
        localStorage.setItem('password', password)
        localStorage.setItem('isLoggedIn', 'true')

        onLoginSuccess({ username, password })
        onClose()
      } else {
        setError(data.message?.error || '登录失败')
      }
    } catch (err) {
      console.error('登录错误:', err)
      setError('网络错误，请稍后重试')
    } finally {
      setIsLoading(false)
    }
  }

  // 注册
  const handleRegister = async (e) => {
    e.preventDefault()
    setError('')

    if (!username.trim()) {
      setError('请输入用户名')
      return
    }
    if (!password.trim()) {
      setError('请输入密码')
      return
    }
    if (password.length < 4) {
      setError('密码长度至少4位')
      return
    }

    setIsLoading(true)
    try {
      // 1. 注册
      const response = await fetch(`${BASE_URL}/api/user/register`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
        credentials: 'include'
      })

      const data = await response.json()

      if (data.success) {
        // 2. 注册成功后调用登录接口来创建 session，以便创建歌单
        try {
          const loginResponse = await fetch(`${BASE_URL}/api/user/login/password`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
            credentials: 'include'
          })

          const loginData = await loginResponse.json()

          if (loginData.success) {
            // 3. 登录成功后创建 favorite 歌单
            try {
              await fetch(`${BASE_URL}/api/user/songLists/add`, {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                },
                body: JSON.stringify({ listName: 'favorite' }),
                credentials: 'include'
              })
            } catch (err) {
              console.error('创建 favorite 歌单失败:', err)
            }
          }
        } catch (err) {
          console.error('自动登录失败:', err)
        }

        // 4. 注册成功，切换到登录页面让用户手动登录
        setMode('login')
        setUsername('')
        setPassword('')
        setError('')
        showToast('注册成功！请使用您的账号密码登录', 'success')
      } else {
        setError(data.message?.error || '注册失败')
      }
    } catch (err) {
      console.error('注册错误:', err)
      setError('网络错误，请稍后重试')
    } finally {
      setIsLoading(false)
    }
  }

  // 切换登录/注册模式
  const switchMode = () => {
    setMode(mode === 'login' ? 'register' : 'login')
    setError('')
    setUsername('')
    setPassword('')
  }

  return (
    <>
      <div className="login-overlay" onClick={isLoading ? undefined : onClose}></div>
      <div className="login-popup">
        <LoadingOverlay isLoading={isLoading} />
        <button className="login-close" onClick={isLoading ? undefined : onClose} disabled={isLoading}>✕</button>

        <div className="login-content">
          <h2 className="login-title">
            {mode === 'login' ? '登录' : '注册'}
          </h2>
          <p className="login-desc">
            {mode === 'login'
              ? '请输入用户名和密码登录'
              : '创建一个新账号'}
          </p>

          <form className="login-form" onSubmit={mode === 'login' ? handlePasswordLogin : handleRegister}>
            <div className="login-input-group">
              <input
                className="login-input"
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder=" "
                required
              />
              <label className="login-label" htmlFor="username">用户名</label>
            </div>

            <div className="login-input-group">
              <input
                className="login-input"
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder=" "
                required
              />
              <label className="login-label" htmlFor="password">密码</label>
            </div>

            {error && <div className="login-error">{error}</div>}

            <button type="submit" className="login-submit">
              {mode === 'login' ? '登录' : '注册'}
            </button>
          </form>

          <div className="login-footer">
            {mode === 'login' ? (
              <>
                还没有账号？
                <button className="login-switch" onClick={switchMode}>
                  立即注册
                </button>
              </>
            ) : (
              <>
                已有账号？
                <button className="login-switch" onClick={switchMode}>
                  去登录
                </button>
              </>
            )}
          </div>
        </div>
      </div>
    </>
  )
}

export default Login
