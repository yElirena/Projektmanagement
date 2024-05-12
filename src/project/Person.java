package project;


/**
 * The Person class represents a person entity with various attributes and getter and setter.
 */
public class Person 
{
	private int personid;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String fax;
	private String sex;
	private String username;
	private String password;
	
	
	public int getPersonid() 
	{
		return personid;
	}
	public void setPersonid(int personid) 
	{
		this.personid = personid;
	}
	public String getFirstname() 
	{
		return firstname;
	}
	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}
	public String getLastname() 
	{
		return lastname;
	}
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	public String getFax() 
	{
		return fax;
	}
	public void setFax(String fax) 
	{
		this.fax = fax;
	}
	public String getSex() 
	{
		return sex;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}

}
