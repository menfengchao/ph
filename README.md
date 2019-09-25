#  程序之家项目 本人于20190830开始编写
* **已督促自己学已致用**

* **新增模块**
    * 1:增加了swagger-ui 使用第三方UI界面
    * 2:增加了权限控制集成了 jjwt 和 Security
    * 3:取消了jjwt 因为这个不好处理退出和刷新有效期 使用redis代替
    * redis中耍了个小聪明 将userId作为value key是userId和一个随机的6为int类型的数加密而来
    * 这样就可以重复利用未过期的sessionId只有当主动退出或者失效后重新生成新的sessionId，
    * redis中也没有多余的数据（内存很宝贵的奥，对于大量用户登录来说），只用sessionId和
    * userId可以相互查询,鉴权时使用sessionId查询出userId即可，登录时 根据userId 和随机
    * 密钥算出sessionId,当然这的是知道用户名和密码的前提下。
 
#  2019/9/20
*  名称很关键 抽时间整理了以下包名     

# 对于集群启动可配置多文件
   >>> idea配置： run/Dubug configurations -> configuration->environment->Program arguments    
   >>> 内容： --spring.profiles.active=10000
