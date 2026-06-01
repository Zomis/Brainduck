import { defineConfig } from 'vite'
import ViteFonts from 'unplugin-fonts/vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [
    vue(),
    ViteFonts({
      fontsource: {
        families: [
          {
            name: 'Roboto',
            weights: [100, 300, 400, 500, 700, 900],
            styles: ['normal', 'italic'],
          },
        ],
      },
    }),
  ],
})