import java.util.*;
/**
 * The test main class 
 * @author Emirhan Uzun / 171044019
 * @since 05/22/2020
 * 
 */
public class Test {

	/**
	 * @param args String arguments
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Library library = new Library();
		
		System.out.println("Please enter a name for admin : ");
		String name = scan.nextLine();
		System.out.println("Plase enter a password for admin : ");
		String password = scan.nextLine();
		
		Admin admin = new Admin(name,password);
		library.setAdmin(admin);
		
		library.menu();
		
		
		scan.close();
	}

}
