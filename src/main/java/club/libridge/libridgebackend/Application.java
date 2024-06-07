package club.libridge.libridgebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

    // private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // applicationContext = SpringApplication.run(Application.class, args);
        SpringApplication.run(Application.class, args);
        // displayAllBeans();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    // public static void displayAllBeans() {
    //     String[] allBeanNames = applicationContext.getBeanDefinitionNames();
    //     for (String beanName : allBeanNames) {
    //         System.out.println(beanName);
    //     }
    // }

}
