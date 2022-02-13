package br.com.forum.config.validation.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Configurações de autorização
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/topic").permitAll()
                .antMatchers(HttpMethod.GET, "/topic/*").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //Configurações de autenticacao
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    //Configurações de recursos estáticos(js, css, imagens)
    }
}
