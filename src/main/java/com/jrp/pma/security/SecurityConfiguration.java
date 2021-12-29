package com.jrp.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("select username, password,enabled "+
                "from users where username = ?")
            .authoritiesByUsernameQuery("select username, authority "+
                "from authorities where username = ?")
        ;

//        auth.jdbcAuthentication().dataSource(dataSource)
//            .usersByUsernameQuery("select username, password,enabled "+
//                "from user_accounts where username = ?")
//            .authoritiesByUsernameQuery("select username, role "+
//                "from user_accounts where username = ?")
//            .dataSource(dataSource)
//            .passwordEncoder(getPasswordEncoder());
//

//        auth.jdbcAuthentication().dataSource(dataSource)
//            .withDefaultSchema()
//            .withUser("myuser")
//            .password("pass")
//            .roles("USER")
//            .and()
//            .withUser("myuser2")
//            .password("pass2")
//            .roles("USER")
//            .and()
//            .withUser("managerUser")
//            .password("pass3")
//            .roles("ADMIN")
//            ;

//        auth.inMemoryAuthentication()
//            .withUser("myuser")
//            .password("pass")
//            .roles("USER")
//            .and()
//            .withUser("myuser2")
//            .password("pass2")
//            .roles("USER")
//            .and()
//            .withUser("managerUser")
//            .password("pass3")
//            .roles("ADMIN")
//        ;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.authorizeRequests()
            .antMatchers("/projects/new").hasRole("ADMIN")
            .antMatchers("/employees/new").hasRole("USER")
            .antMatchers("/","/**")
            .authenticated()
//            .permitAll()
            .and()
            .formLogin();

//        http.csrf().disable();
//        http.headers().frameOptions().disable();
    }
}