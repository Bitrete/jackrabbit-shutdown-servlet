package ru.bitrete.content.repository.management;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.jackrabbit.api.JackrabbitRepository;

public class BitreteServletContextListener implements ServletContextListener {

	private static final String REPOSITORY_JNDI_NAME = "repository-jndi-name";
	private static final String DEFAULT_JNDI_NAME = "jcr/repository";
	
	public BitreteServletContextListener() {
		super();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		try {
			InitialContext ctx = new InitialContext(); 
			Context env = (Context) ctx.lookup("java:comp/env");
			
			String repositoryJndiName = getRepositoryJndiName(servletContext);
			
			servletContext.log(String.format("Searching repository by name '%s'.", 
					repositoryJndiName));
			
			JackrabbitRepository repository = (JackrabbitRepository) 
					env.lookup(repositoryJndiName);
			
			if (repository == null) {
				servletContext.log(String.format(
						"Repository not found by name '%s'. Nothing to do.", repositoryJndiName));
			}
			else {
				servletContext.log("Repository found. Shutting down.");
				
				repository.shutdown();
				
				servletContext.log("Repository shut down.");
			}
		}
		catch (NamingException ex) {
			servletContext.log("Context resolution failure while looking up repository.", ex);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		servletContext.log(String.format("Listener '%s' registered and initialized.", 
				BitreteServletContextListener.class.getName()));
	}

	private String getRepositoryJndiName(ServletContext context) {
		String configuredRepositoryJndiName = context.getInitParameter(REPOSITORY_JNDI_NAME);
		if (configuredRepositoryJndiName != null && configuredRepositoryJndiName.length() > 0)
			return configuredRepositoryJndiName;
			
		context.log("Repository JNDI name is not set. Using default.");
		
		return DEFAULT_JNDI_NAME;
	}
}
