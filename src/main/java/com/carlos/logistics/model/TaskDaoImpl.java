package com.carlos.logistics.model;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Provides functionality for manipulation with tasks using the persistence
 * context from {@link Resources}.
 * 
 */
@Stateful
public class TaskDaoImpl implements TaskDao {

	@Inject
	private EntityManager em;

	@Override
	public void createTask(Task task) {
		em.persist(task);
	}

	@Override
	public List<Task> getAll() {
		return null;
	}

}