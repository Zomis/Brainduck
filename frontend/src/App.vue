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
        <button class="toolbar-btn" @click="runCode">Run</button>
        <button class="toolbar-btn" @click="stopRunning" :disabled="!running">Pause</button>
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
      <div class="left-panel">
        <MemoryCells
          :memory-cells="memoryCells"
        />
      </div>

      <!-- Right Panel - Code Editor -->
      <div class="right-panel">
        <CodeEditor
          v-model="code"
          :highlight-function="highlightCode"
          language="brainfuck"
        />
      </div>
    </div>

    <div class="output">
      <div class="title">Output:</div>
      <div class="content">
        <textarea
          ref="textareaRef"
          v-model="outputText"
          class="editor-textarea"
        >
        </textarea>
      </div>
    </div>

    <!-- Status Bar -->
    <div class="status-bar">
      <div class="status-left">
        <span>(status-bar)</span>
      </div>
      <div class="status-right">
        <span>line {{ 'x' }}, pos {{ cursorPos }}, col {{ cursorCol }}</span>
        <div class="progress-bar">
          <div class="progress-fill" :style="{ width: progressPercent + '%' }"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, type Ref } from 'vue'
import CodeEditor from './components/CodeEditor.vue'
import MemoryCells from './components/MemoryCells.vue'
import type { MemoryCell } from './components/MemoryCell.ts'

// @ts-ignore
import { BrainduckApp } from './app/Brainduck-app.mjs'
const brainduck = BrainduckApp.getInstance()

let worker = new Worker("/worker/Brainduck-worker.mjs", { type: 'module'});
console.log("worker created");
worker.onmessage = e => {
  let type = JSON.parse(e.data).type
  let event = brainduck.parseWorkerEvent(e.data)
  console.log("MAIN:", type, e.data);
  switch (type) {
    case "memory":
      // TODO: This is super slooooooooow
      memoryCells.value[event.address].value = event.value
      break;
    case "pointer":
      memoryPointer.value = event.address
      break;
    case "runStatus":
      running.value = event.running
      break;
    case "output":
      outputText.value = outputText.value + String.fromCodePoint(event.value)
      break;
  }
}

function runCode() {
  outputText.value = ""
  worker.postMessage(brainduck.codeUpdate(code.value));
  worker.postMessage(brainduck.runUntilEnd());
  console.log("worker posted");
}

function stopRunning() {
  try {
    console.log("posting pause");
    worker.postMessage(brainduck.pause());
    console.log("pause posted");
  } catch (err) {
    console.error("postMessage failed:", err);
  }
}

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

const outputText = ref('')
const cursorPos = ref(1028)
const cursorCol = ref(5)
const progressPercent = ref(60)

const running = ref(false)

const memoryPointer = ref(0)
const memoryArray = new Array(30000);
for (let i = 0; i < memoryArray.length; i++) {
  memoryArray[i] = { index: i, value: 0, name: '' }
}

// Memory cells data
const memoryCells: Ref<Array<MemoryCell>> = ref(memoryArray)
//  { index: 1, value: 10, name: '[countdown:1]' },
//  { index: 2, value: 0, name: '[B:7]' },
//  { index: 3, value: 5, name: 'x' },
//  { index: 4, value: 0, name: '[activatedTemp2:1, activatedT]' },


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

.left-panel {
  width: 400px;
  border-right: 1px solid #ddd;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background: #fff;
}

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

.output {
  border-top: 1px solid #ddd;
  padding: 8px;
  background: #fafafa;
  max-height: 500px;
  overflow-y: auto;
}

.output textarea {
  width: 100%;
}

.output .title {
  font-size: 11px;
  font-weight: 600;
  margin-bottom: 4px;
  color: #666;
}

.output .content {
  font-size: 12px;
  font-family: monospace;
}
</style>

