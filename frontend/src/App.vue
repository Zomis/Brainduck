<template>
  <div id="app">
    <h1>Brainfuck Code Editor</h1>
    <CodeEditor
      v-model="code"
      :highlight-function="highlightCode"
      language="brainfuck"
    />
    <div class="info">
      <p>Brainfuck editor with custom syntax highlighting!</p>
      <p>Special characters are highlighted: + - < > , . in purple; [ ] in bold blue.</p>
      <p>Move the caret to [ or ] to see bracket matching with yellow background.</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import CodeEditor from './components/CodeEditor.vue'

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
  let depth = 0
  
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
h1 {
  margin-bottom: 2rem;
}

.info {
  margin-top: 2rem;
  padding: 1rem;
  background: #f0f0f0;
  border-radius: 8px;
}

.info p {
  margin: 0.5rem 0;
  color: #666;
}

#app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
}
</style>

