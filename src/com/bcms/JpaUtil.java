package com.bcms;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class JpaUtil {
	
	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("BemAliPersistence");
	}

	public static EntityManager getGerenciadorEntidades() {
		return factory.createEntityManager();
	}

	public static void close() {
		factory.close();
	}
}
