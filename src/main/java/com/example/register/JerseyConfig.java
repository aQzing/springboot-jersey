package com.example.register;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * @author Qzing 注册资源
 */
@Configuration
//添加请求到达jersey的过滤前缀，防止开始html等被过滤掉，默认是过滤所有资源
@ApplicationPath("webapp")
public class JerseyConfig extends ResourceConfig {
	// 构造函数，在这里注册需要使用的内容，（过滤器，拦截器，API等）
	 public JerseyConfig() {  
         //register(RequestContextFilter.class);  
         //配置restful package.  
         packages("com.example"); 
         register(MultiPartFeature.class);
      }  
	 

}
