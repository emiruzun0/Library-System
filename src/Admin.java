/**
 * The admin class to hold the admin informations. It extends from library class
 * @author Emirhan Uzun / 171044019
 * @since 05/22/2020
 * 
 */
public class Admin extends Library{
	/**
	 * Admin name 
	 */
	protected String name;
	/**
	 * Admin password
	 */
	protected String password;

	/**
	 * This is no parameter constructor for admin.
	 */
	public Admin() {
	}

	/**
	 * This is the parameter constructor.
	 * @param name It is admin name
	 * @param password It is admin surname
	 */
	public Admin(String name,String password) {
		this.name = name;
		this.password = password;
		System.out.println("The new admin profile was created!\n" +
				"Name : " + name + "\nPassword : " + password);
	}



}