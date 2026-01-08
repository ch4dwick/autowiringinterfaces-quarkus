package com.verteregs;

import java.util.Map;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import io.quarkus.arc.ClientProxy;

/**
 * This is where all the magic happens.
 * 
 * @author Chad
 *
 */
@ApplicationScoped
public class InterfaceLoader {
	/**
	 * All the instances of your interface are here. :)
	 */
	@Inject
	protected Instance<Interface> interfaces;

	private Map<String, Interface> interfaceMap;

	@PostConstruct
	void init() {
		interfaceMap = interfaces.stream()
				.collect(Collectors.toMap(i -> ClientProxy.unwrap(i)
						.getClass()
						.getSimpleName(), i -> i));
	}

	/**
	 * Load a concrete instance of the Interface interface.
	 * 
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException Exception returned when a class doesn't exist.
	 */
	public Interface loadClass(String clazz) throws ClassNotFoundException {
		return interfaces
				.stream()
				.filter(type -> ClientProxy.unwrap(type).getClass().getSimpleName().equals(clazz))
				.findFirst()
				.orElseThrow(() -> new ClassNotFoundException(clazz + " no concrete class defined."));
	}

	public Interface loadClass2(String clazz) throws ClassNotFoundException {
		Interface i = interfaceMap.get(clazz);
		if (i == null)
			throw new ClassNotFoundException(clazz + " not defined");
		return i;
	}
}