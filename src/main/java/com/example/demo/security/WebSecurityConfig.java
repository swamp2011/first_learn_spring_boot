package com.example.demo.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 描述：security配置类
 * @author ay
 * @date   2017/12/10.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public CustomUserService customUserService(){
//        return new CustomUserService();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //路由策略和访问权限的简单配置
        http
//                .authorizeRequests()
//                //要求有管理员的权限
//                .antMatchers("/shutdown","/metrics","/health").access("hasRole('ADMIN')")
//                .antMatchers("/**").permitAll()
//                .and()
                .formLogin()                      //启用默认登陆页面
//                .successHandler(new ForwardAuthenticationSuccessHandler("/ayUser/test"))
                .failureUrl("/login?error")     //登陆失败返回URL:/login?error
                .defaultSuccessUrl("/ayUser/test")  //登陆成功跳转URL，这里调整到用户首页
//                .successForwardUrl("/ayUser/test")  //登陆成功跳转URL，这里调整到用户首页

                .permitAll()                    //登陆页面全部权限可访问
                .and().csrf().ignoringAntMatchers("/druid/*");
        super.configure(http);
    }
    /**
     * 配置内存用户
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }
                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return s.equals(charSequence.toString());
                }
            })
                .withUser("阿毅").password("123456").roles("ADMIN")
                .and()
                .withUser("阿兰").password("123456").roles("USER")
                .and()
                .withUser("ay").password("1").roles("ADMIN");
    }
}