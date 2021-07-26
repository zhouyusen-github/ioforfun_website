module.exports = {
    devServer: {
        port: 8090,     // 开发测试时部署的端口号
        proxy: {
            '/category': {
                target: 'http://localhost:8080/',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/category': '/category'
                }
            },
            '/verificationCode': {
                target: 'http://localhost:8080/',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/verificationCode': '/verificationCode'
                }
            },
            '/game': {
                target: 'http://localhost:8080/',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/game': '/game'
                }
            },
            '/user': {
                target: 'http://localhost:8080/',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/user': '/user'
                }
            },
            '/favorite': {
                target: 'http://localhost:8080/',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/favorite': '/favorite'
                }
            }
        }
    }
};
