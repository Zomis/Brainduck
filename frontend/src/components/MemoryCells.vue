<template>
  <div ref="containerRef" class="memory-cells">
    <v-data-table-virtual
      :headers="headers"
      :items="memoryCells"
      :height="tableHeight"
      :item-height="36"

      class="memory-table"
      density="compact"
      item-value="index"
      fixed-header
    >
      <template v-slot:item="{ internalItem, props, itemRef }">
        <tr v-bind="props" :ref="itemRef" :class="{ pointer: internalItem.raw.index == memoryPointer }">
          <td>{{ internalItem.raw.index }}</td>
          <td>{{ internalItem.raw.value }}</td>
          <td>{{ internalItem.raw.name }}</td>
        </tr>
      </template>
    </v-data-table-virtual>
  </div>
</template>

<script setup lang="ts">
import { onBeforeUnmount, onMounted, ref } from 'vue'
import type { DataTableHeader } from 'vuetify'
import type { MemoryCell } from "./MemoryCell.ts";

interface Props {
  memoryPointer: Number
  memoryCells: MemoryCell[]
}

defineProps<Props>()

const containerRef = ref<HTMLElement | null>(null)
const tableHeight = ref(1)
let resizeObserver: ResizeObserver | null = null

const headers = [
  { title: 'Address', align: 'start', key: 'index' },
  { title: 'Value', align: 'start', key: 'value' },
  { title: 'Name', align: 'start', key: 'name' },
] satisfies DataTableHeader<MemoryCell>[]

function updateTableHeight() {
  tableHeight.value = Math.max(1, Math.floor(containerRef.value?.clientHeight ?? 0))
}

onMounted(() => {
  updateTableHeight()

  if (containerRef.value) {
    resizeObserver = new ResizeObserver(updateTableHeight)
    resizeObserver.observe(containerRef.value)
  }
})

onBeforeUnmount(() => {
  resizeObserver?.disconnect()
})
</script>

<style scoped>
.pointer td {
  background-color: #ddd;
}

.memory-cells {
  display: flex;
  flex: 1 1 auto;
  flex-direction: column;
  min-height: 0;
  overflow: hidden;
}

.memory-table {
  flex: 1 1 auto;
  min-height: 0;
  font-size: 12px;
}

.memory-table :deep(tbody tr) {
  cursor: pointer;
}

.memory-table :deep(tbody tr:hover) {
  background: #f5f5f5;
}
</style>
