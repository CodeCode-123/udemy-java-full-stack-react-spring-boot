import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import tailwindcss from "@tailwindcss/vite"

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), tailwindcss()],
  build: {
    outDir: "dist",
    sourcemap: false,
    minify: "esbuild",
    rollupOptions: {
      output: {
        manualChunks(id) {
          if (id.includes('react') || id.includes('react-dom')) {
            return 'vendor';
          }
          if (id.includes('@reduxjs/toolkit') || id.includes('react-redux')) {
            return 'redux';
          }
          if (id.includes('react-router-dom')) {
            return 'router';
          }
          if (id.includes('@fortawesome/react-fontawesome') || id.includes('@fortawesome/fontawesome-svg-core')) {
            return 'ui';
          }
        },
      },
    },
  },
  base: "/",
  server: { port: 5173 },
  preview: { port: 5173 }
});
