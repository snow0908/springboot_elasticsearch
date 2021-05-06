package com.snow.learn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*@Value("${upload.path}")
    private String uploadPath;*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /home/file/**为前端URL访问路径 后面 file:xxxx为本地磁盘映射
        //http://localhost:8080/home/file/%E6%96%87%E4%B9%A6.html
//        registry.addResourceHandler("/home/file/**").addResourceLocations("file:C:" + uploadPath);
        registry.addResourceHandler("/home/file/**").addResourceLocations("file:C:/Users/ASUS/Desktop/test/");
        /*String os = System.getProperty("os.name");
        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/smallapple/**")
                    // /apple/**表示在磁盘apple目录下的所有资源会被解析为以下的路径
                    //媒体资源
                    .addResourceLocations("file:G:/itemsource/smallapple/")
                    //swagger2页面
                    .addResourceLocations("classpath:/META-INF/resources/");
        } else {  //linux 和mac
            registry.addResourceHandler("/smallapple/**")
                    //媒体资源
                    .addResourceLocations("file:/resources/smallapple/")
                    //swagger2页面;
                    .addResourceLocations("classpath:/META-INF/resources/");
        }*/
    }

}

