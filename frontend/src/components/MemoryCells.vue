<template>
  <div class="data-table">
    <v-data-table-virtual
      :headers="headers"
      :items="memoryCells"
      height="800"
      item-value="index"
      fixed-header
    ></v-data-table-virtual>

  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import type { MemoryCell } from "./MemoryCell.ts";

interface Props {
  memoryCells?: MemoryCell[]
}

const props = withDefaults(defineProps<Props>(), {
  memoryCells: () => [],
})

const headers = [
  { title: 'Address', align: 'start', key: 'index' },
  { title: 'Value', align: 'start', key: 'value' },
  { title: 'Name', align: 'start', key: 'name' },
]

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

