import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Test from './components/test.jsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Test />
  </StrictMode>
)
