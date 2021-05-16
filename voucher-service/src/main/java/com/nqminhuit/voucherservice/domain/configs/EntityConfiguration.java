package com.nqminhuit.voucherservice.domain.configs;

import com.nqminhuit.voucherservice.domain.auditor.EntityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class EntityConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }

}
