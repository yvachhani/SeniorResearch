package cA_Evacuation;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Student {

	private Grid<Object> grid;
	
	public Student(Grid<Object> grid) {
		this.grid = grid;
	}
	
	@ScheduledMethod(start = 1, interval = 1)
	public void move() {
		
		GridPoint pt = grid.getLocation(this);
		grid.moveTo(this,pt.getX(), pt.getY() + 1);
	}
	
}
