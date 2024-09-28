const {createProxyMiddleware} = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        '/our_insurance',
        createProxyMiddleware({
            target: 'http://localhost:40021',
            changeOrigin: true,
        })
    );
};