package cA_Evacuation;

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
	public Context build(Context<Object> context) {

		//must match in content.xml file
		context.setId("EvacuationBuilder");
		
		GridFactory gridFactory = GridFactoryFinder.createGridFactory(null);
		Grid<Object> grid = gridFactory.createGrid("grid", context, new GridBuilderParameters<Object>(
				new StrictBorders(), new SimpleGridAdder<Object>(), false, 50, 50));
		
		Student s1 = new Student(grid);
		
		Desk d1 = new Desk(grid);
		Desk d2 = new Desk(grid);
		Desk d3 = new Desk(grid);
		
		context.add(s1);
		context.add(d1);
		context.add(d2);
		context.add(d3);
		
		grid.moveTo(s1, 25,0);
		grid.moveTo(d1, 24,30);
		grid.moveTo(d2, 25,30);
		grid.moveTo(d3, 26,30);
		
		return context;
	}

}
