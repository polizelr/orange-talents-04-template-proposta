package br.com.zupacademy.rafaela.proposta.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/api/v1/proposta/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST, "/api/v1/proposta/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST, "/api/v1/biometria/**").hasAuthority("SCOPE_proposta")
                        .antMatchers(HttpMethod.POST, "/api/v1/bloqueio/**").hasAuthority("SCOPE_proposta")
                        .anyRequest().authenticated()
        )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
