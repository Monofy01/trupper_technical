package com.trupper.brian.security.config.security;

import com.trupper.brian.security.config.security.filter.JwtAuthenticationFilter;
import com.trupper.brian.utils.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class HttpSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationProvider provider, JwtAuthenticationFilter authenticationFilter, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher h2RequestMatcher = new MvcRequestMatcher(introspector, "/**");
        h2RequestMatcher.setServletPath("/h2-console");

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManConfig -> sessionManConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(provider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(builderRequestMatchers(h2RequestMatcher));

        http.headers().frameOptions().disable();


        return http.build();
    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> builderRequestMatchers(MvcRequestMatcher h2RequestMatcher) {
        return authConfig -> {
            authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
            authConfig.requestMatchers(HttpMethod.GET, "/auth/public-access").permitAll();
            authConfig.requestMatchers(h2RequestMatcher).permitAll();
            authConfig.requestMatchers("/error").permitAll();

            authConfig.requestMatchers(HttpMethod.POST, "/api/v1/store/guardar").hasAuthority(Permission.CREATE_LISTA_COMPRAS.name());
            authConfig.requestMatchers(HttpMethod.GET, "/api/v1/store/obtener-lista/**").hasAuthority(Permission.GET_LISTA_COMPRAS.name());
            authConfig.requestMatchers(HttpMethod.PUT, "/api/v1/store/actualizar-lista/**").hasAuthority(Permission.UPDATE_LISTA_COMPRAS.name());
            authConfig.requestMatchers(HttpMethod.DELETE, "/api/v1/store/borrar-lista/**").hasAuthority(Permission.DELETE_LISTA_COMPRAS.name());

            authConfig.anyRequest().denyAll();
        };
    }
}
