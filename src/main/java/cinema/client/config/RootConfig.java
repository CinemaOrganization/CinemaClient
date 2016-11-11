package cinema.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.MultipartConfigElement;

@Configuration
@ComponentScan(basePackages = {"cinema.client"},
        excludeFilters = {
                @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
public class RootConfig{

//        @Bean
//        public MultipartResolver multipartResolver(){
//            StandardServletMultipartResolver standardServletMultipartResolver = new StandardServletMultipartResolver();
//                return standardServletMultipartResolver;
//        }
//
//        @Bean
//        public MultipartConfigElement multipartConfigElement(){
//            return new MultipartConfigElement(null,5000000,5000000,0);
//        }
}