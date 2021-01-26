package pl.edu.wsb.projectmanagement.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import pl.edu.wsb.projectmanagement.service.UserService;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    UserService userService;

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").authenticated()
//                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .failureForwardUrl("/login?error=true")
                .successForwardUrl("/")
                .passwordParameter("password")
                .usernameParameter("username")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .csrf().disable();
    }



    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("swidaSuperAdmin")
                .password("swidaCorporation852456")
                .roles("ADMIN")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
        // check query !!!!!!
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from usr where username=?")
                .authoritiesByUsernameQuery("select username from usr where username=?")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());//inner join user_role ur on u.id=ur.user_id where u.username=?
    }



}