package com.rjs.sandbox.javers1;

import com.rjs.sandbox.javers1.model.Course;
import com.rjs.sandbox.javers1.model.Student;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created on 8/9/2017.
 *
 * @author Randy Joe
 */
@SpringBootApplication
public class MainApp {
	public MainApp() {
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MainApp.class, args);

		try {
			Javers javers = (Javers) ctx.getBean("javers");
			Course c1 = new Course("CS-141", "Thomas");
			Course c2 = new Course("CEG-250", "Garrett");

			Student s1 = new Student("Joe", "Smith");
			s1.getCourses().add(c1);
			s1.getCourses().add(c2);
			Student s2 = new Student("John", "Smith");
			s2.getCourses().add(c1);
			Diff diff = javers.compare(s1, s2);
			Logger logger = Logger.getLogger(MainApp.class.getName());
			logger.info(diff.changesSummary());
			logger.info(diff.prettyPrint());

			List<Change> changes = diff.getChanges();

			if (changes != null) {
				for (Change change : changes) {
					logger.info("Change: " + change);
				}
			}
		}
		catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
