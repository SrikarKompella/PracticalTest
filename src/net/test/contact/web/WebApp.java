package net.test.contact.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebApp implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(SpringMVCConfig.class);
		
		ServletRegistration.Dynamic dispatcherDynamic = 
				servletContext.addServlet("SpringDispatcher", new DispatcherServlet(applicationContext));
		dispatcherDynamic.setLoadOnStartup(1);
		dispatcherDynamic.addMapping("/");
	}

}
