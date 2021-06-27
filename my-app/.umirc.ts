// import { defineConfig } from 'umi';

// export default defineConfig({
//   nodeModulesTransform: {
//     type: 'none',
//   },
//   routes: [
//     { path: '/', component: '@/pages/index' },
//   ],
//   fastRefresh: {},
// });

export default {
  proxy: {
    '/api': {
      target: 'http://localhost:9090',
      changeOrigin: true,
      pathRewrite: { '^/api': '' },
    },

  },
};
