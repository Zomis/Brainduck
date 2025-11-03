<template>
  <div class="memory-cells-panel">
    <div class="data-table">
      <table>
        <thead>
          <tr>
            <th class="index-col"></th>
            <th class="value-col">Value</th>
            <th class="name-col">Name</th>
          </tr>
        </thead>
        <tbody>
          <tr 
            v-for="(cell, index) in memoryCells" 
            :key="index"
            :class="{ 'selected': selectedCellIndex === index }"
            @click="selectedCellIndex = index"
          >
            <td class="index-col">{{ cell.index }}</td>
            <td class="value-col">{{ cell.value }}</td>
            <td class="name-col">{{ cell.name }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="output-section">
      <div class="output-title">Output:</div>
      <div class="output-content">
        <div 
          v-for="(line, index) in outputLines" 
          :key="index"
          :class="{ 'selected': selectedOutputIndex === index }"
          @click="selectedOutputIndex = index"
          class="output-line"
        >{{ line }}</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

interface MemoryCell {
  index: string
  value: string
  name: string
}

interface Props {
  memoryCells?: MemoryCell[]
  outputLines?: string[]
}

const props = withDefaults(defineProps<Props>(), {
  memoryCells: () => [
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
  ],
  outputLines: () => ['3', '5', '8', '13']
})

const selectedCellIndex = ref<number | null>(null)
const selectedOutputIndex = ref<number | null>(null)
</script>

<style scoped>
.memory-cells-panel {
  width: 400px;
  border-right: 1px solid #ddd;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background: #fff;
}

.data-table {
  flex: 1;
  overflow-y: auto;
  overflow-x: hidden;
}

.data-table table {
  width: 100%;
  border-collapse: collapse;
  font-size: 12px;
}

.data-table thead {
  background: #f5f5f5;
  position: sticky;
  top: 0;
  z-index: 1;
}

.data-table th {
  padding: 6px 8px;
  text-align: left;
  border-bottom: 1px solid #ddd;
  font-weight: 600;
  font-size: 11px;
}

.data-table td {
  padding: 4px 8px;
  border-bottom: 1px solid #f0f0f0;
}

.data-table tbody tr {
  cursor: pointer;
}

.data-table tbody tr:hover {
  background: #f5f5f5;
}

.data-table tbody tr.selected {
  background: #e3f2fd;
}

.data-table tbody tr.selected:hover {
  background: #bbdefb;
}

.index-col {
  width: 60px;
  text-align: right;
  color: #666;
}

.value-col {
  width: 80px;
  text-align: right;
}

.name-col {
  flex: 1;
  color: #0066cc;
  font-style: italic;
}

.output-section {
  border-top: 1px solid #ddd;
  padding: 8px;
  background: #fafafa;
  max-height: 200px;
  overflow-y: auto;
}

.output-title {
  font-size: 11px;
  font-weight: 600;
  margin-bottom: 4px;
  color: #666;
}

.output-content {
  font-size: 12px;
  font-family: monospace;
}

.output-line {
  padding: 2px 4px;
  cursor: pointer;
  border-radius: 2px;
}

.output-line:hover {
  background: #f5f5f5;
}

.output-line.selected {
  background: #e3f2fd;
}

.output-line.selected:hover {
  background: #bbdefb;
}
</style>

