package com.carlos.logistics.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.carlos.logistics.model.Task;
import com.carlos.logistics.model.TaskDao;

/**
 * A JAX-RS resource for exposing REST endpoints for Task manipulation
 */
@Path("/")
public class TaskResource {

	@Inject
	private TaskDao taskDao;

	@POST
	@Path("tasks/{title}")
	public Response createTask(
			@PathParam("title") @DefaultValue("task") String taskTitle) {

		Task task = new Task(taskTitle);

		taskDao.createTask(task);

		return Response.ok().build();
	}

	@GET
	@Path("tasks/{id}")
	// JSON: include "application/json" in the @Produces annotation to include
	// json support
	// @Produces({ "application/xml", "application/json" })
	@Produces({ "application/xml" })
	public Task getTaskById(@PathParam("id") Long id) {
		return getTask(id);
	}

	@GET
	@Path("tasks")
	// JSON: include "application/json" in the @Produces annotation to include
	// json support
	// @Produces({ "application/xml", "application/json" })
	@Produces({ "application/xml" })
	public List<Task> getTasks() {
		return taskDao.getAll();
	}

	// Utility Methods

	private Task getTask(Long id) {
		for (Task task : taskDao.getAll())
			if (task.getId().equals(id))
				return task;

		throw new WebApplicationException(Response.Status.NOT_FOUND);
	}

}
