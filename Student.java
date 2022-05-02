import java.io.File;
import java.lang.reflect.Array;
import java.util.*;


public class Student extends Person
{
	private String studentEmail;
	private String[] id;
	// move = new to construcotr
	private ArrayList<Assignment> assignments;
	private ArrayList<String> listOfStudentEmails = new ArrayList<String>();
	private ArrayList<Class> studentClasses = new ArrayList<Class>();
	private Scanner keyboard;

	public Student(String firstName, String lastName)
	{
		super(firstName, lastName);
		setStudentEmail();
		setID();
		assignments = new ArrayList<>();

	}

	public void setStudentEmail()
	{
		char firstLetter = super.getFirstName().charAt(0);
		this.studentEmail = Character.toString(firstLetter) + getLastName() + "@Student.edu";
		System.out.println("Your student email is: " + studentEmail);

	}

	public String getStudentEmail()
	{
		return studentEmail;
	}

	public String getStudentPassword()
	{
		return "Student password: " + super.getPassword();
	}

	public void addEmailToArrayList(String studentEmail)
	{
		listOfStudentEmails.add(studentEmail);
	}

	public String getallStudentEmails()
	{
		if (listOfStudentEmails.size() == 0)
		{
			return "No Emails in array to return";

		} else
		{
			for (int x = 0; x < listOfStudentEmails.size(); x++)
			{
				return listOfStudentEmails.get(x);
			}
		}
		return "no emails";

	}

	public void setID()
	{
		Random random = new Random();
		id = new String[10];
		for (int x = 0; x <= 2; x++)
		{
			id[x] = String.valueOf(0);
		}
		for (int x = 3; x < id.length; x++)
		{
			id[x] = String.valueOf(random.nextInt(9 - 0 + 1) + 0);
		}
	}

	public String getID()
	{
		StringBuilder str = new StringBuilder();
		for (String s : id)
		{
			str.append(s);
		}
		return str.toString();
	}           

	public String viewAssignments(Class sClass)
	{
		return sClass.getAssignments();
	}

	private void submitAssignment(Class sClass)
	{
		System.out.println(viewAssignments(sClass));
		System.out.print("Enter the number of the assignment you are submitting: ");
		int assignment = keyboard.nextInt();
		keyboard.nextLine();
		sClass.submitAssignment(assignment - 1, id);

	}

	public void addClass(Class CRN) {
		studentClasses.add(CRN);
	}
	
	private void displayGrades(Class c) {
		int[] grades = c.getGrades(id);
		for(int i = 0; i < grades.length; i++) {
			System.out.println("Assignment " + (i+1) + " grade: " + grades[i]);
		}
	}

	public int viewClasses()
	{
		
		keyboard = new Scanner(System.in);
		System.out.println("Enter '1' to view a class");
		System.out.println("Enter '2' to register for a class");
		//System.out.println("Enter '3' to go back to the main menu");

		int choice = keyboard.nextInt();
		keyboard.nextLine();
		while (choice != 1 && choice != 2)
		{
			System.out.print("Invalid entry. Please enter 1 or 2: ");
			choice = keyboard.nextInt();
			keyboard.nextLine();
		}
		switch (choice)
		{
		case 1:
			displayClasses();
			System.out.println("Enter the crn of the class you want to view: ");
			int crn = keyboard.nextInt();
			keyboard.nextLine();

			for (Class c: studentClasses)
			{
				if (c.getCRN() == crn) {
					studentClass(c);
					break;
				}    
			}

			break;
		case 2:
			return 1;
		}
		return 0;
	}

	public void displayClasses() {
		StringBuilder allClasses = new StringBuilder("Your classe are: \n");
		for (int i = 0; i < studentClasses.size(); i++)
		{
			allClasses.append(studentClasses.get(i).getCRN() + "\n");
		}
		System.out.println(allClasses.toString());
	}

	private void studentClass(Class c)
	{
		System.out.println("Enter '1' to view assignments");
		System.out.println("Enter '2' submit an assignment");
		System.out.println("Enter '3' to view your grades");
		System.out.println("Enter '4' to choose a different class");
		System.out.println("Enter '5' to return to the main menu");
		int choice = keyboard.nextInt();
		keyboard.nextLine();
		while (choice < 1 || choice > 5)
		{
			System.out.print("Invalid entry. Please enter 1, 2, 3, 4, or 5: ");
			choice = keyboard.nextInt();
			keyboard.nextLine();
		}
		switch (choice)
		{
		case 1:
			System.out.println(viewAssignments(c));
			break;
		case 2:
			submitAssignment(c);
			break;
		case 3:
			displayGrades(c);
			break;
		case 4:
			viewClasses();
			return;
		case 5:
			return;
		}
		studentClass(c);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", ID: " + id.toString();
	}
	
}