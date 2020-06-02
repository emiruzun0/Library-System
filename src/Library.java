import java.util.*;
import java.util.Map.*;
/**
 * The library class 
 * @author Emirhan Uzun / 171044019
 * @since 05/22/2020
 *
 */
public class Library {
	/**
	 * This is the admin object for accessing private methods.
	 */
	protected Admin admin;
	/**
	 * Scanner for take input from user
	 */
	Scanner scan = new Scanner(System.in);

	//Create map in map. The inner map stores the outer map's values
	Map<String, Map<String,Set<String>>> outerMap = new HashMap<String, Map<String,Set<String>>>();
	

	//Outer Map : 	the author name is used as a key, the value is another map whose keys are book names


	/**
	 * Sets the admin object 
	 * @param admin Admin object to initialize
	 */
	public void setAdmin(Admin admin) {
		this.admin = admin;	
	}

	/**
	 * Controls the admin informations to login
	 * @param name Given Name
	 * @param password Given Password
	 * @return true if the informations are matched.
	 */
	public boolean isValid(String name, String password) {
		if(admin.name.equalsIgnoreCase(name)) {
			if(admin.password.equalsIgnoreCase(password)) return true;
			return false;
		}
		
		return false;
	}


	/**
	 * The library menu 
	 */
	public void menu() {
		try{
			int choice;
			boolean flag = true;

			while (flag) {
				System.out.println("--------------------LIBRARY AUTOMATION SYSTEM----------------------");
				System.out.printf("1. Search Book by Author\n2. Search Book by Book Title\n3. Update (Admin Login)\n4. Show all books\n5. Exit");
				System.out.println("\n-------------------------------------------------------------------");
				System.out.println("Please enter choice  : ");

				choice = scan.nextInt();

				switch (choice) {
				case 1:
					searchByAuthor();
					break;
				case 2:
					searchByTitle();
					break;
				case 3:
					System.out.println("Please enter your name : ");
					scan.nextLine();
					String name = scan.nextLine();
					System.out.println("Please enter the your password : ");
					String password = scan.nextLine();
					if(isValid(name,password)) adminPanel();
					else{
						System.out.println("The name or password are false ! ");
					}
					break;
				case 4:
					printAll();
					break;
				case 5:
					System.out.println("Exiting..!");
					flag = false;
					break;
				default:
					System.out.println("You've choosen wrong input");
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		

	}

	/**
	 * From the menu, if the user choose the admin panel then this method works
	 */
	public void adminPanel() {
		boolean flag = true;
		int choice;

		while(flag) {
			System.out.printf("1. Add Book\n2. Delete Book\n3. Update Information\n4. Exit");
			System.out.println("\n--------------------------------------------------------");
			System.out.println("\nPlease select menu : ");
			try{
				choice = scan.nextInt();
				switch (choice) {
				case 1:
					addBook();
					break;
				case 2:
					deleteBook();
					break;
				case 3:
					updateStatus();
					break;
				case 4:
					System.out.println("Backing to main menu...");
					flag = false;
					break;
				default:
					System.out.println("Invalid choice : ");
					break;
				}
			}catch(Exception e) {
				System.out.println("This choice is not right ! The program is shutting down");
			}


		}
	}

	/**
	 * The menu for update book informations.
	 */
	private void updateStatus() {
		boolean flag = true;
		int choice;

		try{
			while(flag) {
				System.out.printf("1. Change Book Name\n2. Change Author Name\n3. Change Location\n4. Exit");
				System.out.println("\nPlease select menu : ");
				choice = scan.nextInt();

				switch (choice) {
				case 1:
					changeBookName();
					break;
				case 2:
					changeAuthorName();
					break;
				case 3:
					changeLocation();
					break;
				case 4:
					System.out.println("Backing to admin menu...");
					flag = false;
					break;
				default:
					System.out.println("You've entered wrong choice ! ");
					break;
				}
			}
		
		}catch(Exception e) {
			System.out.println(e);
		}
			

	}



	/**
	 * Search the book information with author name
	 */
	public void searchByAuthor() {
		if(outerMap.isEmpty() ) {
			System.out.println("The library is empty ! ");
			return ;
		}
		else {
			boolean flag = true;
			int choice;
			System.out.println("Please enter author name: \n");
			scan.nextLine();
			String author = scan.nextLine();
			if(outerMap.containsKey(author)) {
				Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();
				tempMap = outerMap.get(author);

				int count = 0;
				ArrayList<String> tempList = new ArrayList<String>();
				for (String b : tempMap.keySet()) {
					tempList.add(b);
					System.out.println("Book "+(++count)+" : " + b);
				}	
				while(flag) {
					System.out.printf("Which book would you like to see location ? Press 0 for exit : ");
					System.out.println("Please enter choice : ");
					try{
						choice = scan.nextInt();
						switch (choice) {
						case 0:
							System.out.println("Backing to main menu...");
							flag = false;
							break;
						default: 
							if(choice < 0 || choice > tempMap.size() + 1 ) {
								System.out.println("The choice is false ! ");
								flag = false;
							}
							else {
								String choiceBook = tempList.get(choice-1);
								for(Entry<String, Set<String>> m: tempMap.entrySet()) {
									if(m.getKey().equals(choiceBook)) {
										System.out.println("Book : " + m.getKey() + "\nLocation(s) : " + m.getValue().toString());
									}           
								}
							}
							break;
						}
					}catch(Exception e) {
						System.out.println("Please enter right number ! ");
					}


				}
			}
			else System.out.println("This author was not found ! ");
		}
	}

	/**
	 * Search the book information with book name
	 */
	private void searchByTitle() {
		if(outerMap.isEmpty() ) {
			System.out.println("The library is empty ! ");
			return ;
		}
		else {
			boolean flag = false;
			System.out.println("Please enter book name: ");
			scan.nextLine();
			String title = scan.nextLine();
			Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();
			for(Entry<String, Map<String,Set<String>>> m: outerMap.entrySet()) {
				tempMap = m.getValue();
				if(tempMap.containsKey(title)) {
					System.out.println("Author : " + m.getKey());
					for(Entry<String, Set<String>> n: tempMap.entrySet()) {
						if(n.getKey().equals(title)) {
							System.out.println("\nBook : " + n.getKey()+ 
									"\nLocation(s) : " + n.getValue() );
							flag = true;
						}
					}
				}
			}
			if(!flag) System.out.println("This book was not found ! ");
		}
	}

	/**
	 * Adds the book to library. If the book exists, then adds the same key.
	 */
	public void addBook() {		
		System.out.println("Please input the Book Name : ");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("Please input Author : ");
		String  author = scan.nextLine();
		System.out.println("Please input Location(c*s*.number -> c corridor,s shelf) : ");
		String location = scan.nextLine();


		Set<String> tempSet = new HashSet<String>();
		Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();

		if(outerMap.containsKey(author)) {
			tempMap = outerMap.get(author);
			if(tempMap.containsKey(title)) {
				tempSet.addAll(tempMap.get(title));
				tempSet.add(location);
			}
			else {
				tempSet.add(location);
			}
			tempMap.put(title, tempSet);
			outerMap.replace(author,tempMap);
		}
		else {
			tempSet.add(location);
			tempMap.put(title, tempSet);
			outerMap.put(author, tempMap);
		}
		System.out.println("The book was added !\nName : " + title + "\nAuthor : " + author 
				+ "\nLocation : " + location);
	}

	/**
	 * Deletes the book with use the book and author name.
	 */
	private void deleteBook() {
		if(outerMap.isEmpty()) {
			System.out.println("The library is empty !\n ");
			return;
		}
		System.out.println("Please input the book name to remove : ");
		printBooks();
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("Please input the author name");
		String author = scan.nextLine();
		if(outerMap.containsKey(author)) {
			Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();
			tempMap = outerMap.get(author);
			if(tempMap.containsKey(title)) {
				tempMap.remove(title);
				if(!tempMap.isEmpty()){
					System.out.println("This book was deleted ! These books are left : ");
					outerMap.replace(author, tempMap);
					printBooks();
				}
				else{
					System.out.println("This author has not book anymore. So the author was also removed ");
					outerMap.remove(author);
				}
			}
			else System.out.println("The book was not found ! ");
		}
		else System.out.println("This author was not found ! ");
	}

	/**
	 * Changes the book name if the book exists
	 */
	private void changeBookName() {
		printBooks();
		boolean flag = false;
		System.out.printf("Please enter the input for old book name");
		scan.nextLine();
		String title = scan.nextLine();
		Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();
		for(Entry<String, Map<String,Set<String>>> m: outerMap.entrySet()) {
			tempMap = m.getValue();
			if(tempMap.containsKey(title)) {
				System.out.printf("Please enter the input for new book name");
				String newKey = scan.nextLine();
				Set<String> tempSet = new HashSet<String>();
				tempSet.addAll(tempMap.get(title));
				tempMap.put(newKey, tempSet);
				tempMap.remove(title);
				flag = true;
				outerMap.replace(m.getKey(), tempMap);
				System.out.println("This book name was changed ! ");
				break;
			}
		}
		if(!flag) System.out.println("This book name was not found ! ");


	}

	/**
	 * Change author name 
	 */
	private void changeAuthorName() {
		printAuthors();
		System.out.printf("Please enter the input for old author name");
		scan.nextLine();
		String author = scan.nextLine();
		if(outerMap.containsKey(author)) {
			System.out.printf("Please enter the input for new author name");
			String newKey = scan.nextLine();
			Map<String,Set<String>> obj = outerMap.get(author);
			outerMap.put(newKey,obj);
			outerMap.remove(author);
			System.out.println("This author name was changed ! ");
		}
		else System.out.println("This author name was not found ! ");

	}
	
	private void changeLocation() {
		System.out.println("Please input the book name to change location : ");
		printAll();
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("Please input the author name");
		String author = scan.nextLine();
		if(outerMap.containsKey(author)) {
			Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();
			tempMap = outerMap.get(author);
			if(tempMap.containsKey(title)) {
				Set<String> tempSet = new HashSet<String>();
				tempSet.addAll(tempMap.get(title));
				System.out.printf("Please enter the input old location ");
				String oldLocation = scan.nextLine();
				if(tempSet.contains(oldLocation)) {
					System.out.printf("Please enter the input new location ");
					String newLocation = scan.nextLine();
					tempSet.remove(oldLocation);
					tempSet.add(newLocation);
					tempMap.replace(title, tempSet);
					outerMap.replace(author, tempMap);
					System.out.println("The location was changed ! ");
					printAll();
				}
				else {
					System.out.println("The location was not found !");
				}
			}
			else{
					System.out.println("This book was not found ! ");
			}
		}
		else System.out.println("This author was not found ! ");
		
	}

	/**
	 * Prints the authors name 
	 */
	private void printAuthors() {
		System.out.println("\nAuthors : ");

		for(Entry<String, Map<String,Set<String>>> m: outerMap.entrySet()) {
			System.out.println(" - " + m.getKey());
		}
		System.out.println("---------------------");

	}


	/**
	 * Prints all book informations
	 */
	private void printAll() {
		if(outerMap.isEmpty() ) {
			System.out.println("The library is empty ! ");
			return;
		}
		System.out.println("ALL BOOKS : ");
		System.out.println("Authors - Books - Locations ");
		Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();
		for(Entry<String, Map<String,Set<String>>> m: outerMap.entrySet()) {
			tempMap = m.getValue();
			System.out.println(m.getKey() + 
					"-" + tempMap.keySet() + "-" + tempMap.values() );
			System.out.println("---------------------");

		}
	}

	/**
	 * Prints the book and author names
	 */
	private void printBooks() {
		Map<String,Set<String>> tempMap = new HashMap<String,Set<String>>();
		System.out.println("\nAuthors   -    Books  ");

		for(Entry <String , Map<String, Set<String>>> n: outerMap.entrySet()) {
			tempMap = n.getValue();
			System.out.println(n.getKey() + " - " + tempMap.keySet());
			System.out.println("---------------------");
		}
	}




}

