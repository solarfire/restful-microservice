package sco.co.so.web.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * Docket Bean.
     * Set the documentation type of it.
     * And add customised API documentation info etc.
     * This docket will generate new urls.
     * <p>
     * /swagger-resources/configuration/security
     * /swagger-resources/configuration/ui
     * /swagger-resources
     * /v2/api-docs - this gives you a json representatin of the api ie the RestController.
     * - NB I commented out the com.fasterxml.jackson.dataformat:jackson-dataformat-xml as got
     * - an xml Accept issue in the browser.
     * /swagger-ui.html
     *
     * @return
     */
    @Bean
    public Docket api() {
        // Documentation type of SWAGGER_2

        return new Docket(DocumentationType.SWAGGER_2)
                // And customised api info
                .apiInfo(MY_API_INFO)
                // by default the api says produces */*.  As we handle json and xml
                .consumes(MY_DEFAULT_PRODUCES_CONSUMES)
                .produces(MY_DEFAULT_PRODUCES_CONSUMES);
    }

    /**
     * Customise API documentation.
     */
    private static final ApiInfo MY_API_INFO;
    private static final Contact MY_CONTACT;

    private static final Set<String> MY_DEFAULT_PRODUCES_CONSUMES;

    static {
        MY_CONTACT = new Contact("Todo", "www.todourl.co.uk", "email@todo.co.uk");

        MY_API_INFO = new ApiInfo("My Api Documentation",
                "My appi Documentation",
                "1.0",
                "urn:tos",
                MY_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

        //
        MY_DEFAULT_PRODUCES_CONSUMES = new HashSet<String>(Arrays.asList("application/json", "application/xml"));


    }


}