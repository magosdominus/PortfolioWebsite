package cormierm;

import java.text.DecimalFormat;

/**
 * Description: This class represents a class mark.
 * 
 * @author Matthew Cormier
 * @version 2.0 (02/07/2016)
 * @since 2.0
 */
public class Mark {
	
	// Instance attributes 
	
	/**
	 * Course code number.
	 */
	private String courseCode;
	/**
	 * Name of the College course.
	 */
	private String courseName;
	/**
	 * The mark result from the class.
	 */
	private int result;
	/**
	 * Weighting of the course on the GPA.
	 */
	private float gpaWeighting;
	
	// Getters and setters for each attribute.
	
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public float getGpaWeighting() {
		return gpaWeighting;
	}

	public void setGpaWeighting(float gpaWeighting) {
		this.gpaWeighting = gpaWeighting;
	}

	// Class attributes 
	public static final float MINIMUM_GPA = 0.0f;
	public static final float MAXIMUM_GPA = 5.0f;
	
	// decimal formatting object.
	public static DecimalFormat decimalFormat = new DecimalFormat("0.0");
	
	// Parameterized constructor
	public Mark(String courseCode, String courseName, int result, float gpaWeighting)
	{
		setCourseCode(courseCode);
		setCourseName(courseName);
		setResult(result);
		setGpaWeighting(gpaWeighting);
	}
	
	// overload toString()
	// @return the mark information as a string.
	public String toString() 
	{
		String output = "\t" + getCourseCode() + "  " + String.format("%-35s", getCourseName()) + "  " + getResult() + "  " + decimalFormat.format(getGpaWeighting());
	
		return output;
	}
}
