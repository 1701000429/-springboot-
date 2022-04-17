# -springboot-

2022/4/18 新增功能
>1. 浏览记录
>2. 评论记录
>3. 登陆记录(管理员端)
>4.修复了上次的bug(柱状图显示不全，标签新增时导航不正确，评论时头像显示异常)
>新增功能的controller都放在了userController下面。

## 注意事项
### 1.微信扫描二维码的使用
基于springboot的学术论坛平台。
有一个微信扫描二维码查看的小功能，需要自行修改第几个ip(wifi是第几个网卡)

### 2.文件上传与下载
在file.property配置路径，默认为D:/CQUPTupload
### 3.一些写死的图片
用户的默认头像为 cquptLOGO.jpg  在全局配置文件里面
管理员登陆后的首图欢迎图片写死在 cqupt.jpeg
登陆页面的背景图片为login_bg.jpg 
开发者二维码图片 wechat.jpg
用户首页下载论文的那个图片  download.jpg
## 4.登录
密码采用MD5加密    密码: 123456