package KaiSei;

/**
 * @author Team TA's
 */


import java.util.HashMap;
import java.util.Map;

public abstract class Report {

	// visible to subclasses
	protected Schedule schedule;
	protected Map<String, Object> options;
	
	protected void setOptions(Map<String, Object> options)
    {
		if (options == null)
			this.options = new HashMap<String, Object>();
		else
			this.options = options;
    }
	public void askOptionsFromUser()
	{
		// Override in subclasses as needed.
	}
	
	// Made this an abstract method that should be implemented in subclasses
	public abstract String toString();
	
}
