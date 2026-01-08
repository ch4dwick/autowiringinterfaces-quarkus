package com.verteregs;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;


/**
 * Say for example you had one set of business rules to execute. Do them here. 
 * Take special note of the @Component annotation here. Spring won't include this in
 * the @Autowired collection without it.
 * 
 * @author Chad
 *
 */
@ApplicationScoped
public class ConcreteClass1 implements Interface {
	private static final Logger LOGGER = Logger.getLogger(ConcreteClass1.class.getName());

	@Override
	public void doSomething() {
		LOGGER.log(Level.INFO, "I am ConcreteClass1.");
	}
}