package com.example.blog_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/posts/​**​").permitAll() // 根据需求配置权限
                        .anyRequest().authenticated()
                )
                // 默认已启用 CSRF，无需显式调用 .csrf().disable()
                .formLogin(form -> form
                        .loginPage("/login") // 自定义登录页（可选）
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/posts")
                );
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        // 配置一个测试用户
        UserDetails user = User.builder()
                .username("admin") // 自定义用户名
                .password("{noop}123456") // {noop}表示明文密码
                .roles("USER") // 角色
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}