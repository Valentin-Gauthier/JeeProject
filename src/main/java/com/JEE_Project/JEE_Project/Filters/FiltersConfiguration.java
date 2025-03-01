package com.JEE_Project.JEE_Project.Filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfiguration {

    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilter() {
        FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SessionFilter());
        registrationBean.addUrlPatterns("/Profil", "/Programs","/Recommendations");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
