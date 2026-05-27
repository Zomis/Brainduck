<template>
  <div id="app">
    <!-- Menu Bar -->
    <div class="menu-bar">
      <div class="menu-item">File</div>
      <div class="menu-item">Run/Debug</div>
      <div class="menu-item">Help</div>
    </div>

    <!-- Toolbar and Tabs -->
    <div class="toolbar-container">
      <div class="toolbar">
        <button class="toolbar-btn">Fix bug with editing code</button>
        <button class="toolbar-btn">Analyze</button>
      </div>
      <div class="tabs">
        <div class="tab active">untitled</div>
        <div class="tab">untitled</div>
      </div>
    </div>

    <!-- Main Content Area - Split View -->
    <div class="main-content">
      <!-- Left Panel - Data/State View -->
      <MemoryCells
        :memory-cells="memoryCells"
        :output-lines="outputLines"
      />

      <!-- Right Panel - Code Editor -->
      <div class="right-panel">
        <CodeEditor
          v-model="code"
          :highlight-function="highlightCode"
          language="brainfuck"
        />
      </div>
    </div>

    <!-- Status Bar -->
    <div class="status-bar">
      <div class="status-left">
        <span>fibonnaci.bf: LimitedSteps: Completed with 3 steps.</span>
      </div>
      <div class="status-right">
        <span>pos {{ cursorPos }}, col {{ cursorCol }}</span>
        <div class="progress-bar">
          <div class="progress-fill" :style="{ width: progressPercent + '%' }"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import CodeEditor from './components/CodeEditor.vue'
import MemoryCells from './components/MemoryCells.vue'

const code = ref(`++[>++<-]
>+++++++++[<++++++++>-]<.
>+++++++[<++++>-]<+.
+++++++..
+++.
>>++++[<++++>-]<.
>++++++++[<++++>-]
++.
------.
--------.
>>++++++++++[<++++++>-]<.
++.
------.
--------.
>>++[<+++++>-]<.
>++++++++++[<++++++>-]
++.
------.
--------.
-.
>>++++[<++++>-]<+.
`)

const cursorPos = ref(1028)
const cursorCol = ref(5)
const progressPercent = ref(60)

// Memory cells data
const memoryCells = ref([
  { index: '0', value: '248', name: '' },
  { index: '1', value: '10', name: '[countdown:1]' },
  { index: '2', value: '0', name: '[lineBreak:1]' },
  { index: '3', value: '0', name: '[zero:1]' },
  { index: '4', value: '0', name: '[digitStart:1]' },
  { index: '5', value: '1', name: '[A:6]' },
  { index: '6', value: '8', name: '[A_2:6]' },
  { index: '7', value: '0', name: '[B:7]' },
  { index: '8', value: '5', name: 'x' },
  { index: '9', value: '0', name: '[overflow:6]' },
  { index: 'a', value: '3', name: '[C:6]' },
  { index: 'b', value: '10', name: '[10minusC:7]' },
  { index: 'c', value: '0', name: '[digit:1]' },
  { index: 'd', value: '0', name: '[activatedTemp2:1, activatedT]' },
  { index: 'e', value: '1', name: '[digitStart:1]' },
  { index: 'f', value: '1', name: '[A:1]' },
  { index: '10', value: '1', name: '[A_2:1]' },
  { index: '11', value: '1', name: '[B:1]' },
  { index: '12', value: '0', name: '[overflow:1]' },
  { index: '13', value: '1', name: '[C:1]' },
  { index: '14', value: '1', name: '[10minusC:1]' },
  { index: '15', value: '0', name: '[digit:1]' },
  { index: '16', value: '1', name: '' },
  { index: '17', value: '0', name: '' },
  { index: '18', value: '1', name: '' },
  { index: '19', value: '0', name: '' },
  { index: '1a', value: '49', name: '' },
])

const outputLines = ref(['3', '5', '8', '13'])

// Brainfuck syntax highlighting
function highlightCode(text: string, cursorPosition: number): Map<number, string> {
  const highlights = new Map<number, string>()
  
  // Highlight special Brainfuck characters: + - < > , . (purple)
  const specialChars = ['+', '-', '<', '>', ',', '.']
  for (let i = 0; i < text.length; i++) {
    if (specialChars.includes(text[i])) {
      highlights.set(i, 'bf-operation')
    }
  }
  
  // Highlight brackets: [ ] (bold blue)
  for (let i = 0; i < text.length; i++) {
    if (text[i] === '[' || text[i] === ']') {
      highlights.set(i, 'bf-bracket')
    }
  }
  
  // Mark non-Brainfuck characters as gray
  const brainfuckChars = new Set(['+', '-', '<', '>', ',', '.', '[', ']', '\n', '\r', '\t', ' '])
  for (let i = 0; i < text.length; i++) {
    if (!brainfuckChars.has(text[i]) && !highlights.has(i)) {
      highlights.set(i, 'bf-other')
    }
  }
  
  // Find matching bracket when cursor is on [ or ]
  if (cursorPosition >= 0 && cursorPosition < text.length) {
    const charAtCursor = text[cursorPosition]
    
    if (charAtCursor === '[') {
      // Find matching ]
      const matchPos = findMatchingBracket(text, cursorPosition)
      if (matchPos !== -1) {
        highlights.set(cursorPosition, 'bf-bracket-match')
        highlights.set(matchPos, 'bf-bracket-match')
      }
    } else if (charAtCursor === ']') {
      // Find matching [
      const matchPos = findMatchingBracket(text, cursorPosition)
      if (matchPos !== -1) {
        highlights.set(cursorPosition, 'bf-bracket-match')
        highlights.set(matchPos, 'bf-bracket-match')
      }
    }
  }
  
  return highlights
}

// Helper function to find matching bracket
function findMatchingBracket(text: string, position: number): number {
  const char = text[position]
  const direction = char === '[' ? 1 : -1
  let depth = 1
  
  for (let i = position + direction; i >= 0 && i < text.length; i += direction) {
    if (text[i] === '[') {
      depth += direction
    } else if (text[i] === ']') {
      depth -= direction
    }
    
    if (depth === 0) {
      return i
    }
  }
  
  return -1
}
</script>

<style scoped>
#app {
  display: flex;
  flex-direction: column;
  height: 100vh;
  width: 100vw;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background: #ffffff;
  color: #000000;
  font-family: system-ui, -apple-system, sans-serif;
}

/* Menu Bar */
.menu-bar {
  display: flex;
  background: #f5f5f5;
  border-bottom: 1px solid #ddd;
  padding: 4px 8px;
  gap: 16px;
  user-select: none;
}

.menu-item {
  padding: 4px 8px;
  cursor: pointer;
  font-size: 13px;
}

.menu-item:hover {
  background: #e0e0e0;
}

/* Toolbar and Tabs */
.toolbar-container {
  display: flex;
  background: #fafafa;
  border-bottom: 1px solid #ddd;
  align-items: center;
  padding: 4px 8px;
  gap: 16px;
}

.toolbar {
  display: flex;
  gap: 8px;
}

.toolbar-btn {
  padding: 4px 12px;
  border: 1px solid #ccc;
  background: #fff;
  cursor: pointer;
  font-size: 12px;
  border-radius: 3px;
}

.toolbar-btn:hover {
  background: #f0f0f0;
}

.tabs {
  display: flex;
  gap: 0;
  margin-left: auto;
}

.tab {
  padding: 6px 16px;
  background: #e8e8e8;
  border: 1px solid #ccc;
  border-right: none;
  cursor: pointer;
  font-size: 12px;
  user-select: none;
}

.tab:last-child {
  border-right: 1px solid #ccc;
}

.tab.active {
  background: #fff;
  border-bottom: 1px solid #fff;
  margin-bottom: -1px;
}

/* Main Content Area */
.main-content {
  display: flex;
  flex: 1;
  overflow: hidden;
  border-bottom: 1px solid #ddd;
}

/* Right Panel */
.right-panel {
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background: #fff;
}

.right-panel :deep(.code-editor-container) {
  height: 100%;
  border: none;
  border-radius: 0;
  box-shadow: none;
}

.right-panel :deep(.editor-wrapper) {
  height: 100%;
  min-height: 0;
}

/* Status Bar */
.status-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #f5f5f5;
  border-top: 1px solid #ddd;
  padding: 4px 12px;
  font-size: 11px;
  height: 24px;
}

.status-left {
  flex: 1;
}

.status-right {
  display: flex;
  align-items: center;
  gap: 12px;
}

.progress-bar {
  width: 200px;
  height: 16px;
  background: #e0e0e0;
  border: 1px solid #ccc;
  border-radius: 2px;
  overflow: hidden;
  position: relative;
}

.progress-fill {
  height: 100%;
  background: #0066cc;
  transition: width 0.2s;
}
</style>

