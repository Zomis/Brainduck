# AI Duck - Code Editor with Custom Syntax Highlighting

A Vue 3 code editor component that gives you **full control** over syntax highlighting. Highlight code dynamically as users edit text or move the caret.

## Features

- ✅ **Custom Syntax Highlighting** - Complete control over when and how code is highlighted
- ✅ **Real-time Updates** - Highlighting updates on every keystroke and cursor movement
- ✅ **Line Numbers** - Optional line number display
- ✅ **Vue 3 Composition API** - Built with modern Vue 3 features
- ✅ **TypeScript Support** - Fully typed components
- ✅ **Customizable Styling** - Easy to theme and customize

## Getting Started

### Install Dependencies

```bash
npm install
```

### Run Development Server

```bash
npm run dev
```

The application will be available at `http://localhost:5173`

### Build for Production

```bash
npm run build
```

## Usage

The `CodeEditor` component accepts a custom highlight function that gives you complete control:

```vue
<template>
  <CodeEditor
    v-model="code"
    :highlight-function="myHighlightFunction"
    language="typescript"
    :show-line-numbers="true"
  />
</template>

<script setup lang="ts">
import { ref } from 'vue'
import CodeEditor from './components/CodeEditor.vue'

const code = ref('const hello = "world"')

// Your custom highlight function
function myHighlightFunction(text: string, cursorPosition: number): Map<number, string> {
  const highlights = new Map<number, string>()
  
  // Apply your custom highlighting logic here
  // Return a Map where keys are character positions
  // and values are CSS class names
  
  return highlights
}
</script>
```

## Component Props

| Prop | Type | Default | Description |
|------|------|---------|-------------|
| `modelValue` | `string` | required | The code content (v-model) |
| `highlightFunction` | `(text: string, cursorPosition: number) => Map<number, string>` | required | Your custom highlighting function |
| `language` | `string` | `'plaintext'` | Language identifier (for reference only) |
| `showLineNumbers` | `boolean` | `true` | Show line numbers |
| `fontSize` | `number` | `14` | Font size in pixels |
| `fontFamily` | `string` | `'Fira Code', 'Courier New', monospace` | Font family |

## How It Works

The editor uses a dual-layer approach:
1. **Textarea Layer** - Handles input, cursor, and scrolling
2. **Highlight Layer** - Renders syntax highlighting on top

This approach ensures:
- Perfect text selection
- Native keyboard shortcuts
- Accurate cursor positioning
- Smooth scrolling

The highlight function is called on:
- Every keystroke (`input` event)
- Every cursor movement (`keyup`, `click`, `keydown` events)
- External code changes (`watch`)

## Customization

### Adding Highlight Classes

Edit the CSS in `CodeEditor.vue` to add your own highlight classes:

```css
.highlight-layer :deep(.my-custom-class) {
  color: #ff6b6b;
  font-weight: bold;
}
```

### Styling the Editor

All styling is scoped to the component and can be customized:

```vue
<style scoped>
.editor-wrapper {
  background: #1e1e1e; /* Dark theme */
  border: 1px solid #444;
}

.line-numbers {
  background: #252526;
  color: #858585;
}
</style>
```

## Example Highlight Function

Here's a complete example highlighting TypeScript:

```typescript
function highlightCode(text: string, cursorPosition: number): Map<number, string> {
  const highlights = new Map<number, string>()
  
  // Keywords
  ['function', 'interface', 'const', 'let', 'return'].forEach(keyword => {
    const regex = new RegExp(`\\b${keyword}\\b`, 'g')
    let match
    while ((match = regex.exec(text)) !== null) {
      for (let i = 0; i < match[0].length; i++) {
        highlights.set(match.index + i, 'keyword')
      }
    }
  })
  
  // Types
  const typeRegex = /:\s*(\w+)/g
  let match
  while ((match = typeRegex.exec(text)) !== null) {
    for (let i = 0; i < match[1].length; i++) {
      highlights.set(match.index + 2 + i, 'type')
    }
  }
  
  // Strings
  const stringRegex = /(["'`])(?:(?=(\\?))\2.)*?\1/g
  while ((match = stringRegex.exec(text)) !== null) {
    for (let i = 0; i < match[0].length; i++) {
      highlights.set(match.index + i, 'string')
    }
  }
  
  // Comments
  const commentRegex = /\/\/.*$/gm
  while ((match = commentRegex.exec(text)) !== null) {
    for (let i = 0; i < match[0].length; i++) {
      highlights.set(match.index + i, 'comment')
    }
  }
  
  return highlights
}
```

## Project Structure

```
AI-duck/
├── src/
│   ├── components/
│   │   └── CodeEditor.vue      # The main editor component
│   ├── App.vue                 # Demo app with example usage
│   ├── main.ts                 # Application entry point
│   ├── style.css               # Global styles
│   └── env.d.ts                # TypeScript declarations
├── package.json
├── tsconfig.json
├── vite.config.ts
└── README.md
```

## License

MIT

## Author

Created with ❤️ for custom code editing experiences

