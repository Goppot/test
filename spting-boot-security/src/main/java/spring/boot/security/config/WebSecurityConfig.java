package spring.boot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import spring.boot.security.service.UserServiceImp;

import java.util.function.Function;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImp userServiceImp;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//Включаем авторизацию
                    .antMatchers("/","/registration/**").permitAll()//Разрешаем полнай доступ без авторизации "/"
                    .antMatchers("/admin/**").hasRole("ADMIN")//Доступ только для ROLE_ADMIN
                    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")////Доступ только для ROLE_USER, ROLE_ADMIN
                    .anyRequest().authenticated()//Для всех остальных требуем авторизацию
                .and()
                    .formLogin()//Включаем форм логин
                    .loginPage("/login")//Форм логин находится по этому адресу
                    .successHandler(new SuccessUserHandler())
                    .permitAll()//Разрешаем полнай доступ без авторизации "/login"
                .and()
                    .logout()//Включает логаут
                    .permitAll()//Разрешаем полнай доступ без авторизации "логаут"
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImp)
                .passwordEncoder(encoder());
    }

    @Bean
        public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
