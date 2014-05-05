package org.jboss.as.quickstarts.tasksrs;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.carlos.logistics.model.Resources;
import com.carlos.logistics.model.Task;
import com.carlos.logistics.model.TaskDao;
import com.carlos.logistics.model.TaskDaoImpl;

/**
 * @author Lukas Fryc
 * @author Oliver Kiss
 */
@RunWith(Arquillian.class)
public class TaskDaoTest {

	@Deployment
	public static WebArchive deployment() throws IllegalArgumentException,
			FileNotFoundException {
		return new DefaultDeployment()
				.withPersistence()
				.withImportedData()
				.getArchive()
				.addClasses(Resources.class, Task.class, TaskDao.class,
						TaskDaoImpl.class);
	}

	@Inject
	private EntityManager em;

	@Inject
	private TaskDao taskDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void user_should_be_created_with_one_task_attached()
			throws Exception {
		// given
		Task task = new Task("New task");

		// when
		taskDao.createTask(task);
		List<Task> userTasks = em.createQuery(
				"SELECT t FROM Task t WHERE t.owner = :owner", Task.class)
				.getResultList();

		// then
		assertEquals(1, userTasks.size());
		assertEquals(task, userTasks.get(0));
	}

	@Test
	public void all_tasks_should_be_obtained_from_detachedUser() {
		// when
		List<Task> userTasks = taskDao.getAll();

		// then
		assertEquals(2, userTasks.size());
	}

}
