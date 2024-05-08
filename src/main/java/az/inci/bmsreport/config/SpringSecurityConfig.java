package az.inci.bmsreport.config;

import az.inci.bmsreport.handler.AuthSuccessHandler;
import az.inci.bmsreport.handler.ReportAccessDeniedHandler;
import az.inci.bmsreport.service.security.BMSUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig
{
    private final AuthSuccessHandler successHandler;
    private final ReportAccessDeniedHandler accessDeniedHandler;
    private final BMSUserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        http.csrf(AbstractHttpConfigurer::disable);
        http.requiresChannel(registry -> registry.anyRequest().requiresSecure());
        http.userDetailsService(userDetailsService);
        http.authorizeHttpRequests(matcherRegistry -> matcherRegistry
                .requestMatchers("/login",
                                "/logout",
                                "/403",
                                "/image/**",
                        "/css/**",
                        "/js/**",
                        "/reports/frame/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated());
        http.formLogin(configurer -> configurer.loginPage("/login")
                                               .permitAll()
                                               .successHandler(successHandler));
        http.logout(LogoutConfigurer::permitAll);
        http.headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        http.exceptionHandling(configurer -> configurer.accessDeniedHandler(accessDeniedHandler));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new CustomPasswordEncoder();
    }

    private static class CustomPasswordEncoder implements PasswordEncoder
    {

        @Override
        public String encode(CharSequence rawPassword)
        {
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword)
        {
            return rawPassword.equals(encodedPassword);
        }
    }
}
