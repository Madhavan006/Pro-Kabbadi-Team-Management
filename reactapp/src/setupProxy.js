const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'https://8080-bfabcadddbdfbaeebddbeceabcefefcd.premiumproject.examly.io',
      changeOrigin: true,
      secure: false,
    })
  );
};