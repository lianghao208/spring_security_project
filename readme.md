解绑微信：DELETE请求 http://www.pinzhi365.com/connect/weixin

配置session失效页面：my.security.browser.session.sessionInvalidUrl

使用浏览器模式，修改demo项目的pom.xml文件，将dependency改成browser项目

使用QQ、微信登录，将demo项目的端口改成80，并使用www.pinzhi365.com域名登陆（修改host文件）

http://localhost:8080/oauth/authorize?response_type=code&client_id=imooc&redirect_uri=http://example.com&scope=all


### 0x00密码模式

POST http://127.0.0.1:8080/oauth/token

HEADERS:

	Content-Type:application/x-www-form-urlencoded

	Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=

	(Username:imooc
	Password:imoocsecret)

BODY:

	grant_type:password

	username:tom

	password:123456

	scope:all

Response:（jwt令牌: https://www.jsonwebtoken.io/ 进行解密）
	
	{
		"access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0b20iLCJzY29wZSI6WyJhbGwiXSwiY29tcGFueSI6ImNpdGljIGJhbmsiLCJleHAiOjE1MTcxMjk3MTQsImF1dGhvcml0aWVzIjpbImFkbWluIiwiUk9MRV9VU0VSIl0sImp0aSI6IjdiMzcwNzk1LTVkYjItNGQ5OC04YjY5LTBhYmMxNDQxMDJhNSIsImNsaWVudF9pZCI6Imltb29jIn0.qYMDpp9w267WPKPaOhXqpe4RGPCRkkHd1SXeHzcwAwA",
		"token_type": "bearer",
		"refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0b20iLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiN2IzNzA3OTUtNWRiMi00ZDk4LThiNjktMGFiYzE0NDEwMmE1IiwiY29tcGFueSI6ImNpdGljIGJhbmsiLCJleHAiOjE1MTk3MTgxMTQsImF1dGhvcml0aWVzIjpbImFkbWluIiwiUk9MRV9VU0VSIl0sImp0aSI6ImI4NGI0MDNlLWRkZWQtNDVkNy04ZjZlLTNlN2MyY2Y2ODEyMiIsImNsaWVudF9pZCI6Imltb29jIn0.NxaJk4HWH7HyFmTquvDYqELUA1sP168FFqTsWAKbnMU",
		"expires_in": 3599,
		"scope": "all",
		"company": "citic bank",
		"jti": "7b370795-5db2-4d98-8b69-0abc144102a5"
	}

### 0x01授权码模式

POST http://127.0.0.1:8080/oauth/token

HEADERS:

	Content-Type:application/x-www-form-urlencoded

	Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=

	(Username:imooc
	Password:imoocsecret)

BODY:

	grant_type:authorization_code

	code:ZdBCWr

	redirect_uri:http://example.com

	client_id:imooc

	scope:all

### 0x02表单登录

POST http://127.0.0.1:8080/authentication/form

HEADERS:

	Content-Type:application/x-www-form-urlencoded

	Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=

	(Username:imooc
	Password:imoocsecret)

BODY:

	username:tom

	password:123456

### 0x03短信验证码发送

GET http://127.0.0.1:8080/code/sms?mobile=13012345678

HEADERS:

	deviceId:007

BODY:

### 0x04短信验证码登录

POST http://127.0.0.1:8080/authentication/mobile

HEADERS:

	Content-Type:application/x-www-form-urlencoded

	Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=

	(Username:imooc
	Password:imoocsecret)

	deviceId:007

BODY:
	
	mobile:13012345678（从发送验证码的提交参数中得到，绑定session）

	smsCode:073357（从后台日志中得到）

### 0x05获取用户信息

GET http://127.0.0.1:8080/user/me

HEADERS:

	Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=

	(Username:imooc
	Password:imoocsecret)

BODY:

### 0x06OpenId登录

POST http://127.0.0.1:8080/authentication/openid

HEADERS:

	Content-Type:application/x-www-form-urlencoded

	Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=

	(Username:imooc
	Password:imoocsecret)

BODY:

	providerId:weixin

	openId:1234567
	
### 0x07刷新Token

POST http://127.0.0.1:8080/oauth/token

HEADERS:

	Content-Type:application/x-www-form-urlencoded

	Authorization:Basic aW1vb2M6aW1vb2NzZWNyZXQ=

	(Username:imooc
	Password:imoocsecret)

BODY:

	grant_type:refresh_token

	refresh_token:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0b20iLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiN2IzNzA3OTUtNWRiMi00ZDk4LThiNjktMGFiYzE0NDEwMmE1IiwiY29tcGFueSI6ImNpdGljIGJhbmsiLCJleHAiOjE1MTk3MTgxMTQsImF1dGhvcml0aWVzIjpbImFkbWluIiwiUk9MRV9VU0VSIl0sImp0aSI6ImI4NGI0MDNlLWRkZWQtNDVkNy04ZjZlLTNlN2MyY2Y2ODEyMiIsImNsaWVudF9pZCI6Imltb29jIn0.NxaJk4HWH7HyFmTquvDYqELUA1sP168FFqTsWAKbnMU（jwt密码模式得到的jwt令牌refresh_token）

	scope_all:all
