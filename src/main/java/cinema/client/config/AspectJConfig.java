package cinema.client.config;

import cinema.client.logging.Logging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("cinema.client.logging")
public class AspectJConfig {

    @Bean
    public Logging logging(){
        return new Logging();
    }
}
