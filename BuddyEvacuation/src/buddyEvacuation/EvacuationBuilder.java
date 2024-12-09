package buddyEvacuation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.StrictBorders;

public class EvacuationBuilder implements ContextBuilder<Object> {

	@Override
	public Context<Object> build(Context<Object> context) {
		// Code that is ran when initializing the Repast simulation

		context.setId("EvacuationBuilder"); // must match config.xml

		// Create grid dimensions
		int xMax = 50;
		int yMax = 50;
		int[] dimensions = { xMax, yMax };

		// Create grid with specified dimensions
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context, new GridBuilderParameters<Object>( // Id must match
																										// projection in
																										// config.xml
				new StrictBorders(), new SimpleGridAdder<Object>(), false, dimensions)); // Strict borders means the grid
																						// does not wrap at the edges;
																						// No pac-man

		// Create 20 students and desks and place students behind each desk
		Student[] s = new Student[20];

		// Assign ID to student before shuffling array
		for (int i = 0; i < s.length; i++) {
			s[i] = new Student(grid);
			s[i].ID = i;
		}

		// Assign each student a buddy
		for (int i = 0; i < s.length; i++) {
			if (i % 2 == 0)
				s[i].buddy = s[i + 1];
			else
				s[i].buddy = s[i - 1];
		}
		// Shuffle array. Used to randomly place buddy groups in classroom
//		List<Student> list = Arrays.asList(s);
//		Collections.shuffle(list);
//		s = list.toArray(new Student[20]);

		// Add students to context and locate them on the grid
		for (int i = 0; i < 10; i++) {
			context.add(s[i]);
			grid.moveTo(s[i], 2 + (5 * i), 10);
		}

		for (int i = 10; i < 20; i++) {
			context.add(s[i]);
			grid.moveTo(s[i], 2 + (5 * (i - 10)), 30);
		}

		Desk[] d = new Desk[20];
		for (int i = 0; i < 10; i++) {
			d[i] = new Desk();
			context.add(d[i]);
			grid.moveTo(d[i], 2 + (5 * i), 11);
		}

		for (int i = 10; i < 20; i++) {
			d[i] = new Desk();
			context.add(d[i]);
			grid.moveTo(d[i], 2 + (5 * (i - 10)), 31);
		}

		Student.context = context;

		return context;
	}

}
