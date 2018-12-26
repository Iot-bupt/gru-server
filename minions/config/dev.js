var path = require('path');

module.exports = {
    port: 40000,
    viewEngine: 'ejs',

    views: path.resolve(__dirname, '..', 'views'),
    staticPath: path.resolve(__dirname, '..', 'public'),

    env: 'dev',
    logfile: path.resolve(__dirname, '..', 'logs/access.log'),

    sessionSecret: 'session_secret_random_seed',

    //redis config
    redis: {"address": "172.30.62.5", "port": "6379", "passwd": ""},

    //不需要过滤是否登陆状态的白名单
    whitelist: [
        "/",
        "/auth/login",
        "/version"
    ],

    zk: {
        addr: "172.30.62.6:2181",
        spears: "/spearnodes_dev"
    },

    statServer: "http://127.0.0.1:50000"

};