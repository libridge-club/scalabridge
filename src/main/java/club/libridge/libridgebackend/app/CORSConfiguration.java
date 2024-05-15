package club.libridge.libridgebackend.app;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// @EnableWebMvc
public class CORSConfiguration implements WebMvcConfigurer {

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**");
    // }

    /**
    *  Total customization - see below for explanation.
    */
//   @Override
//   public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//     configurer.favorPathExtension(true).
//         favorParameter(false).
//         parameterName("mediaType").
//         ignoreAcceptHeader(false).
//         useJaf(false).
//         useRegisteredExtensionsOnly(false).
//         defaultContentType(MediaType.APPLICATION_JSON).
//         mediaType("xml", MediaType.APPLICATION_XML).
//         mediaType("json", MediaType.APPLICATION_JSON);
//   }
}
