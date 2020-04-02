const {override, fixBabelImports,addDecoratorsLegacy, addLessLoader, addWebpackAlias, overrideDevServer} = require('customize-cra');
const path = require('path');

const addProxy = () => config => {
    config.proxy = {
        '/api': {
            target: 'http://localhost:8080',
            pathRewrite: {'^/api' : ''},
            changeOrigin: true,
            secure: false
        }
    }
    return config;
};

module.exports = {
    webpack: override(
        fixBabelImports('import', {
            libraryName: 'antd',
            libraryDirectory: 'es',
            style: true
        }),
        addDecoratorsLegacy(),
        addLessLoader({
            javascriptEnabled: true
        }),
        addWebpackAlias({
            '@': path.resolve(__dirname, 'src')
        })
    ),
    devServer: overrideDevServer(addProxy())
};
