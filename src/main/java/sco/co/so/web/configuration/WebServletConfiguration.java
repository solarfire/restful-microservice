package sco.co.so.web.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created on 31/10/2017.
 * By implementing {@code WebApplicationInitializer} we replace web.xml
 * Here we provide our {@link WebConfiguration} class and add DispatcherServlet, which acts as the FrontController of the Spring MVC application.
 * {@link WebConfiguration} class is the source of Spring beans, before which we used contextConfiglocation.
 */
public class WebServletConfiguration implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebConfiguration.class);
        webContext.setServletContext(servletContext);

        System.out.println("\n\n\n*****************\nonStartup\n\n\n");


        System.out.println("######  #######  #####  ####### ");
                System.out.println("#     # #       #     #    #");
                        System.out.println("#     # #       #          #");
                                System.out.println("######  #####    #####     #");
                                        System.out.println("#   #   #             #    #");
                                                System.out.println("#    #  #       #     #    #");
                                                        System.out.println("#     # #######  #####     #");

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}

