解绑微信：DELETE请求 http://www.pinzhi365.com/connect/weixin

配置session失效页面：my.security.browser.session.sessionInvalidUrl

使用浏览器模式，修改demo项目的pom.xml文件，将dependency改成browser项目

使用QQ、微信登录，将demo项目的端口改成80，并使用www.pinzhi365.com域名登陆（修改host文件）

http://localhost:8080/oauth/authorize?response_type=code&client_id=imooc&redirect_uri=http://example.com&scope=all