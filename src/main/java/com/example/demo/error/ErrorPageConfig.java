package com.example.demo.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        ErrorPage error404Page=new ErrorPage(HttpStatus.NOT_FOUND,"/404.html" );
//        ErrorPage error404Page=new ErrorPage(HttpStatus.NOT_FOUND,"/ayUser/test" );
//        ErrorPage error400Page=new ErrorPage(HttpStatus.BAD_REQUEST,"/error401" );
//        ErrorPage error401Page=new ErrorPage(HttpStatus.UNAUTHORIZED,"/error401");
//        ErrorPage error500Page=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error500");
        registry.addErrorPages(error404Page);
//        registry.addErrorPages(error400Page,error401Page,error500Page);
    }

}

//package com.example.demo.error;
//
//        import org.springframework.context.annotation.Bean;
//        import org.springframework.context.annotation.Configuration;
//        import org.springframework.http.HttpStatus;
//
///**
// * 描述：自定义错误页面
// * @author Ay
// * @date   2017/12/02
// */
    // 用EmbeddedServletContainerCustomizer注册error page的方式，
    // spring-boot 2.0还能用，但2.0以上版本就不能用了
//    @Configuration
//    public class ErrorPageConfig {
//
//        @Bean
//        public EmbeddedServletContainerCustomizer containerCustomizer() {
//
//            return new EmbeddedServletContainerCustomizer() {
//                @Override
//                public void customize(ConfigurableEmbeddedServletContainer container) {
//                    ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
//                    container.addErrorPages(error404Page);
//                }
//            };
//        }
//}
