<template>
  <div class="code-editor-container">
    <div class="editor-wrapper" @click="handleClick">
      <textarea
        ref="textareaRef"
        v-model="localCode"
        @input="handleInput"
        @keyup="handleKeyup"
        @keydown="handleKeydown"
        @scroll="handleScroll"
        class="editor-textarea"
        :style="textareaStyle"
      ></textarea>
      <div 
        ref="highlightLayerRef"
        class="highlight-layer"
        v-html="highlightedCode"
        :style="layerStyle"
      ></div>
      <div class="line-numbers" v-if="showLineNumbers">
        <div
          v-for="n in lineCount"
          :key="n"
          class="line-number"
        >{{ n }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch, nextTick } from 'vue'

interface Props {
  modelValue: string
  highlightFunction: (text: string, cursorPosition: number) => Map<number, string>
  language?: string
  showLineNumbers?: boolean
  fontSize?: number
  fontFamily?: string
}

const props = withDefaults(defineProps<Props>(), {
  language: 'plaintext',
  showLineNumbers: true,
  fontSize: 14,
  fontFamily: "'Fira Code', 'Courier New', monospace"
})

const emit = defineEmits<{
  'update:modelValue': [value: string]
}>()

const textareaRef = ref<HTMLTextAreaElement>()
const highlightLayerRef = ref<HTMLDivElement>()
const localCode = ref(props.modelValue)

const lineCount = computed(() => {
  return localCode.value.split('\n').length
})

const textareaStyle = computed(() => ({
  fontSize: `${props.fontSize}px`,
  fontFamily: props.fontFamily,
  lineHeight: '1.5',
  paddingLeft: props.showLineNumbers ? 'calc(50px + 16px)' : '16px'
}))

const layerStyle = computed(() => ({
  fontSize: `${props.fontSize}px`,
  fontFamily: props.fontFamily,
  lineHeight: '1.5',
  paddingLeft: props.showLineNumbers ? 'calc(50px + 16px)' : '16px'
}))

// Watch for external changes to modelValue
watch(() => props.modelValue, (newValue) => {
  if (newValue !== localCode.value) {
    localCode.value = newValue
    updateHighlighting()
  }
})

watch(localCode, (newValue) => {
  emit('update:modelValue', newValue)
})

const highlightedCode = ref('')

function getCursorPosition(): number {
  if (!textareaRef.value) return 0
  return textareaRef.value.selectionStart
}

function updateHighlighting() {
  const cursorPos = getCursorPosition()
  const highlights = props.highlightFunction(localCode.value, cursorPos)
  
  // Apply highlights
  let html = ''
  for (let i = 0; i < localCode.value.length; i++) {
    const char = localCode.value[i]
    const hlClass = highlights.get(i)
    
    if (char === '\n') {
      if (hlClass) {
        html += `<span class="${hlClass}">\n</span>`
      } else {
        html += '\n'
      }
    } else if (char === ' ') {
      if (hlClass) {
        html += `<span class="${hlClass}"> </span>`
      } else {
        html += ' '
      }
    } else {
      if (hlClass) {
        html += `<span class="${hlClass}">${escapeHtml(char)}</span>`
      } else {
        html += escapeHtml(char)
      }
    }
  }
  
  highlightedCode.value = html
  
  // Sync scroll positions
  nextTick(() => {
    syncScroll()
  })
}

function escapeHtml(text: string): string {
  const div = document.createElement('div')
  div.textContent = text
  return div.innerHTML
}

function syncScroll() {
  if (textareaRef.value && highlightLayerRef.value) {
    highlightLayerRef.value.scrollTop = textareaRef.value.scrollTop
    highlightLayerRef.value.scrollLeft = textareaRef.value.scrollLeft
  }
}

function handleInput() {
  updateHighlighting()
}

function handleKeyup() {
  updateHighlighting()
}

function handleKeydown() {
  // Use setTimeout to get updated cursor position after keydown
  setTimeout(() => {
    updateHighlighting()
  }, 0)
}

function handleScroll() {
  syncScroll()
}

function handleClick() {
  // Use setTimeout to get updated cursor position after click
  setTimeout(() => {
    updateHighlighting()
  }, 0)
}

// Initial highlighting
updateHighlighting()
</script>

<style scoped>
.code-editor-container {
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.editor-wrapper {
  position: relative;
  display: flex;
  min-height: 400px;
}

.editor-textarea {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 16px;
  border: none;
  outline: none;
  resize: none;
  background: transparent;
  color: transparent;
  caret-color: #333;
  z-index: 2;
  font-family: inherit;
  tab-size: 2;
}

.highlight-layer {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 16px;
  pointer-events: none;
  white-space: pre-wrap;
  word-wrap: break-word;
  overflow: hidden;
  z-index: 1;
  font-family: inherit;
}

.line-numbers {
  background: #f5f5f5;
  padding: 16px 8px;
  text-align: right;
  border-right: 1px solid #ddd;
  user-select: none;
  font-family: 'Fira Code', 'Courier New', monospace;
  font-size: 14px;
  line-height: 1.5;
  color: #888;
  z-index: 3;
  min-width: 50px;
}

.line-number {
  min-height: 21px;
}

/* Syntax highlighting classes */
.highlight-layer :deep(.keyword) {
  color: #0066cc;
  font-weight: 600;
}

.highlight-layer :deep(.type) {
  color: #009966;
  font-weight: 600;
}

.highlight-layer :deep(.string) {
  color: #cc0000;
}

.highlight-layer :deep(.comment) {
  color: #888888;
  font-style: italic;
}

.highlight-layer :deep(.cursor-char) {
  background-color: #ffffcc;
  animation: blink 1s infinite;
}

/* Brainfuck syntax highlighting */
.highlight-layer :deep(.bf-operation) {
  color: #8B5CF6;
  font-weight: 500;
}

.highlight-layer :deep(.bf-bracket) {
  color: #0066cc;
  font-weight: bold;
}

.highlight-layer :deep(.bf-bracket-match) {
  color: #0066cc;
  font-weight: bold;
  background-color: #FFEB3B;
  border-radius: 2px;
}

.highlight-layer :deep(.bf-other) {
  color: #999999;
}

@keyframes blink {
  0%, 50% { background-color: #ffffcc; }
  51%, 100% { background-color: transparent; }
}
</style>

