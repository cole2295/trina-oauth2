#Spring Oauth2 实例
##工程结构
工程名          | 说明
---------------|-----------------
 oauth2-server | oauth2 服务器+资源
 oauth2-client | oauth2 客户端
 oauth2-mgr    | oauth2 管理后台
 oauth2-httpclient | oauth2 客户端

##快速运行
    1.修改3个工程下的/src/main/resources/application.properties中的数据库配置信息。
    2.在本地mysql数据库创建oauth2的库，编码为utf-8.
    3.运行oauth2-server中的/src/main/java/com/trinasolar/oauth2/server/Oauth2ServerApplication
    4.运行oauth2-client中的/src/main/java/com/trinasolar/oauth2/client/ClientApplication
    5.运行oauth2-mgr中的/src/main/java/com/trinasolar/oauth2/mgr/OauthMgrApplication
    6.打开浏览器输入http://localhost:9099/home,输入13811111111，密码123456测试。
    7.http://localhost:9090/oauthmgr 为client管理后台。用户名:admin,密码:123456。
    8.当启动oauth2-httpclient工程时浏览器的访问地址为http://localhost:8090/oauthserver/oauth/authorize?client_id=trina_app&redirect_uri=http://localhost:9099/login&response_type=code,且oauth2-client需要关闭。