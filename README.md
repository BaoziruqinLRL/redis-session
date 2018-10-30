# redis-session 工具使用说明
1.本工具为redis-session工具包，以hash结构存储session  
2.本工具是通过httpServletRequest来自动生成session，若想要后台服务器主动生成session，请参考redis-util  
3.session过期时间为2小时

# 使用方式
通过添加依赖
``` JAVA
<dependency>
    <groupId>com.baozi.commons.redis</groupId>
    <artifactId>redis-session</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
方法调用：
``` JAVA
// 生成session，并存储需要的业务对象
RedisSessionHandler.setSessionObject((HttpServletRequest) request,"session_key",new Object());
// 获取业务对象
Object obj = RedisSessionHandler.getSessionObject((HttpServletRequest) request,"session_key");
// 获取sessionId
String sessionId = RedisSessionHandler.getSessionId((HttpServletRequest) request);
```

