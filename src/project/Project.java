package project;


/**
 * The Project class represents a person entity with various attributes as well as getter and setter.
 */
public class Project {
	private int projectID;
	private String acronym;
	private String title;
	private String description;
	private String startdate;
	private String enddate;
	
	
	public int getProjectID() 
	{
		return projectID;
	}
	public void setProjectID(int projectId) 
	{
		this.projectID = projectId;
	}
	
	public String getAcronym() 
	{
		return acronym;
	}
	public void setAcronym(String acronym) 
	{
		this.acronym = acronym;
	}
	
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public String getStartdate() 
	{
		return startdate;
	}
	public void setStartdate(String startdate) 
	{
		this.startdate = startdate;
	}
	public String getEnddate() 
	{
		return enddate;
	}
	public void setEnddate(String enddate) 
	{
		this.enddate = enddate;
	}

}
