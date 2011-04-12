package fi.helsinki.cs.scheduler3000;

/**
 * @author Team TA's
 */


import java.util.HashMap;

public abstract class Report {

	// visible to subclasses
	protected Schedule schedule;
	protected HashMap<String, Object> options;
	
	// Made this an abstract method that should be implemented in subclasses
	public abstract String toString();
	
}
