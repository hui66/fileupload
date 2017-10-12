package cn.Steven;


import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName:App.java
 * @Description:
 * @author Steven
 * @date:2017年7月28日 上午9:08:16
 */
@SpringBootApplication
public class App 
{
	@Bean 
    public MultipartConfigElement multipartConfigElement() { 
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制
        factory.setMaxFileSize("528kB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("1556kB"); 
        //Sets the directory location where files will be stored.
        //factory.setLocation("路径地址");
//        factory.setLocation("D://22");    //无效
        return factory.createMultipartConfig(); 
    } 

	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
