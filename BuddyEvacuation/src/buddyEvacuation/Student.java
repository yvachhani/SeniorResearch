package buddyEvacuation;

import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class Student {

	private Grid<Object> grid;
	private GridPoint destination;
	public boolean exited;
	public boolean hasPanicked;
	public int panicTimer;
	public Student buddy;
	public int ID;

	static GridPoint exit = new GridPoint(24, 49);
	static int studentCount = 0;
	static public Context<Object> context;

	// Initialize Student object with grid and destination, increment student count
	// by one
	public Student(Grid<Object> grid) {
		this.grid = grid;
		this.destination = exit;
		this.exited = false;
		studentCount++;
	}

	// Method that is called at every time step beginning at step 1
	@ScheduledMethod(start = 1, interval = 1)
	public void move() {

		// Check if distance between Student and exit is 0, if so Student is removed
		if (grid.getDistance(this.grid.getLocation(this), exit) == 0) {
			context.remove(this);
			this.exited = true;
			studentCount--;
		}
		// Only execute if Student has not left room
		if (!this.exited) {
			// If student has not panicked, there is a chance for them to panic
			if (!this.hasPanicked) {
				checkPanic();
			}

			// Check distance between self and buddy, remove panic if next to each other
			if (!this.buddy.exited) {
				if (grid.getDistance(this.grid.getLocation(this), this.buddy.grid.getLocation(this.buddy)) <= 1) {
					this.panicTimer = 0;
					this.buddy.destination = exit;
				}
			}

			// If student is not panicked, they move
			if (this.panicTimer == 0) {
				if ((int) (Math.random() * 2) == 0)
					xFirst();
				else
					yFirst();
			} else { // if they are panicked
				this.panicTimer--;
				if (this.panicTimer == 0) // if their panic runs out buddy resumes going to exit
					this.buddy.destination = exit;
			}
		}
		// If no more students end simulation
		if (studentCount == 0) {
			RunEnvironment.getInstance().endRun();

		}

	}

	public void setBuddy(Student buddy) {
		this.buddy = buddy;
	}

	public void checkPanic() {
		//if (RunEnvironment.getInstance().getCurrentSchedule().getTickCount() > 5) {
			if ((int) (Math.random() * 100) < 15) { // Current panic chance is 5%
				// Begin panic for student
				this.panicTimer = 51;
				this.hasPanicked = true;

				// Buddy cannot panic and starts moving towards panicked buddy
				this.buddy.hasPanicked = true;
				this.buddy.destination = this.grid.getLocation(this);
			}
		//}

	}

	// Option between checking x-axis or y-axis first and moving along that axis if
	// not on the current value
	public void xFirst() {
		GridPoint current = grid.getLocation(this);

		if (current.getX() < destination.getX())
			xUp();
		else if (current.getX() > destination.getX())
			xDown();
		else if (current.getY() < destination.getY())
			yUp();
		else if (current.getY() > destination.getY())
			yDown();
	}

	public void yFirst() {
		GridPoint current = grid.getLocation(this);

		if (current.getY() < destination.getY())
			yUp();
		else if (current.getY() > destination.getY())
			yDown();
		else if (current.getX() < destination.getX())
			xUp();
		else if (current.getX() > destination.getX())
			xDown();
	}

	// Movement methods that move the Student on the grid by one
	public void xUp() {
		GridPoint pt = grid.getLocation(this);
		grid.moveTo(this, pt.getX() + 1, pt.getY());

	}

	public void xDown() {
		GridPoint pt = grid.getLocation(this);
		grid.moveTo(this, pt.getX() - 1, pt.getY());
	}

	public void yUp() {
		GridPoint pt = grid.getLocation(this);
		grid.moveTo(this, pt.getX(), pt.getY() + 1);

	}

	public void yDown() {
		GridPoint pt = grid.getLocation(this);
		grid.moveTo(this, pt.getX(), pt.getY() - 1);
	}

}
