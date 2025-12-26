import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    open: true,
    allowedHosts: ['test.myweb2025.xyz'],
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/cover': {
        target: 'http://localhost:8080',
        changeOrigin: true
      },
      '/mp3': {
        target: 'http://localhost:8080',
        changeOrigin: true
      }
    }
  }
})
