package buddyEvacuation;

import java.awt.Color;

import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class StudentStyle extends DefaultStyleOGL2D {

	public Color getColor(Object o) {

		Student student = (Student) o;

		if (student.panicTimer > 0)
			return Color.red;

		if (student.ID < 2)
			return Color.blue;
		else if (student.ID < 4)
			return Color.cyan;
		else if (student.ID < 6)
			return Color.lightGray;
		else if (student.ID < 8)
			return Color.gray;
		else if (student.ID < 10)
			return Color.green;
		else if (student.ID < 12)
			return Color.magenta;
		else if (student.ID < 14)
			return Color.orange;
		else if (student.ID < 16)
			return Color.pink;
		else if (student.ID < 18)
			return Color.yellow;
		else
			return Color.black;

	}

}
