<template>
  <div class="data-table">
    <table>
      <thead>
        <tr>
          <th class="index-col">Address</th>
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
</template>

<script setup lang="ts">
import { ref } from 'vue'
import type { MemoryCell } from "./MemoryCell.ts";

interface Props {
  memoryCells?: MemoryCell[]
}

const props = withDefaults(defineProps<Props>(), {
  memoryCells: () => [],
})

const selectedCellIndex = ref<number | null>(null)
</script>

<style scoped>
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
</style>

