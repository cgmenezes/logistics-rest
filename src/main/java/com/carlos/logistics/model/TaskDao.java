package com.carlos.logistics.model;

import java.util.List;

import javax.ejb.Local;

/**
 * Basic operations for manipulation of tasks
 * 
 */
@Local
public interface TaskDao {

	void createTask(Task task);

	List<Task> getAll();

}
