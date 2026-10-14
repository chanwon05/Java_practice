import { defineConfig } from 'vite';

export default defineConfig({
  server: { proxy: { '/todos': 'http://127.0.0.1:8080' } },
  build: {
    outDir: '../src/main/resources/static',
    assetsDir: 'react-assets',
    emptyOutDir: false,
  },
});
