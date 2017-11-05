package learn.springinaction.soundsystem;

import org.springframework.context.annotation.*;

@Configuration
//@Import(CDConfig.class)
@ImportResource("classpath:cd-config.xml")
//@ComponentScan    // will be commented out for Java configuration
public class CDPlayerConfig {
    @Bean
    public CDPlayer cdPlayer(CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }
}
