import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

	public static ArrayList<Teacher> teachers;
	public static ArrayList<Student> students;
	public static ArrayList<Class> classes;

	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		TeacherDatabase td = new TeacherDatabase();
		StudentDatabase sd = new StudentDatabase();

		teachers = new ArrayList<>();
		students = new ArrayList<>();
		classes = new ArrayList<>();

		classes.add(new Class(2234));
		classes.add(new Class(3421));
		classes.add(new Class(1002));


		System.out.println("Welcome to the student - teacher portal!\n");
		adminMenu(td, sd, keyboard);
		menu(td, sd, keyboard);
	}

	public static void menu(TeacherDatabase td, StudentDatabase sd, Scanner keyboard)
	{

		String input = " ";
		while (!input.equalsIgnoreCase("exit"))
		{
			System.out.println("Type 'Student' if you would like to go to student log in");
			System.out.println("Type 'Teacher' if you would like to go to teacher log in");
			System.out.println("Type 'admin' if you would like to go to admin in");
			System.out.println("Type 'Exit' if you would like to exit");

			input = keyboard.next();
			if (!input.equalsIgnoreCase("exit"))
			{
				handleMenuOptions(input, td, sd, keyboard);
			} else
			{
				System.out.println("Thank you for using ClassManager. Have a great day!");
				System.exit(0);
				
			}
		}
	}

	public static void handleMenuOptions(String input, TeacherDatabase td, StudentDatabase sd, Scanner keyboard)
	{
		if (input.equalsIgnoreCase("student"))
		{
			studentLogin(td, sd, keyboard);
		} else if (input.equalsIgnoreCase("teacher"))
		{
			teacherLogin(td, sd, keyboard);
		} else
		{
			adminMenu(td, sd, keyboard);
		}

	}

	public static void adminMenu(TeacherDatabase td, StudentDatabase sd, Scanner keyboard)
	{

		String input = " ";
		while (!input.equalsIgnoreCase("exit"))
		{
			System.out.println("ADMIN MENU");
			System.out.println("Type 'Student' if you would like to add a student");
			System.out.println("Type 'Teacher' if you would like to add a teacher");
			System.out.println("Type 'main' to return to main menu");
			System.out.println("Type 'Exit' if you would like to exit");

			input = keyboard.next();
			if (!input.equalsIgnoreCase("exit"))
			{
				handleAdminMenuOption(input, td, sd, keyboard);
			} else
			{
				System.exit(0);
			}
		}
		return;

	}

	public static void handleAdminMenuOption(String input, TeacherDatabase td, StudentDatabase sd, Scanner keyboard)
	{
		if (input.equalsIgnoreCase("Student"))
		{
			addStudent(sd, keyboard);
		} else if (input.equalsIgnoreCase("Teacher"))
		{
			addTeacher(td, keyboard);
		}else
		{
			menu(td, sd, keyboard);
		}
	}

	public static void studentLogin(TeacherDatabase td, StudentDatabase sd, Scanner keyboard)
	{
		boolean valid = false;
		String cont = "continue";
		while (!valid && cont.equalsIgnoreCase("continue"))
		{
			System.out.println("Please enter your University Email: ");
			String email = keyboard.next();
			while(studentEmailIsValid(email) == false) {
				System.out.println("Invalid email. Try again: ");
				email = keyboard.next();
				studentEmailIsValid(email);
			}
			System.out.println("Please enter your password: ");
			String password = keyboard.next();
			valid = sd.validateLogin(email, password);
			if (!valid)
			{
				System.out.println("Log in failed. Invalid credentials");
				System.out.println("To try again enter 'continue' ");
				System.out.println("To return to main menu enter 'main' ");
				System.out.println("To exit the program type 'exit'");
				cont = keyboard.next();

				if (cont.equalsIgnoreCase("main"))
				{
					menu(td, sd, keyboard);
					return;
				} else if (cont.equalsIgnoreCase("exit"))
				{
					System.exit(0);
				}
			} else{
				Student student = null;
				for (Student s : students) {
					if (s.getPassword().equals(password)){
						student = s;
						System.out.println("You have successfully logged in!\n");
					}
				}
				if(student == null) {
					System.out.println("Student could not be found.");
					return;
				}
				
				int choice = student.viewClasses();
				while(choice == 1) {
					System.out.println("Which class do you want to regester for? \n" + classes.toString());
					int num = keyboard.nextInt();
					boolean a = regesteringStudent(student, num);
					while(a == false) {
						System.out.println("Enter a valid CRN:  \n" + classes.toString());
						num= keyboard.nextInt();
						a = regesteringStudent(student, num);
					}
					choice = student.viewClasses();
				}
			}


		}

	}

	static boolean studentEmailIsValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@Student.edu";
		return email.matches(regex);
	}
	public static void teacherLogin(TeacherDatabase td, StudentDatabase sd, Scanner keyboard)
	{
		boolean valid = false;
		String cont = "continue";
		while (!valid && cont.equalsIgnoreCase("continue"))
		{
			System.out.println("Please enter your University Email: ");
			String email = keyboard.next();
			while(teacherEmailIsValid(email) == false) {
				System.out.println("Invalid email. Try again: ");
				email = keyboard.next();
				teacherEmailIsValid(email);
			}
			System.out.println("Please enter your password: ");
			String password = keyboard.next();
			valid = td.validateLogin(email, password);
			if (!valid)
			{
				System.out.println("Log in failed. Invalid credentials");
				System.out.println("To try again enter 'continue' ");
				System.out.println("To return to main menu enter 'main' ");
				System.out.println("To exit the program type 'exit'");
				cont = keyboard.next();

				if (cont.equalsIgnoreCase("main"))
				{
					menu(td, sd, keyboard);
					return;
				} else if (cont.equalsIgnoreCase("exit"))
				{
					System.exit(0);
				}
			} else {
				Teacher teacher = null;
				for (Teacher t : teachers)
				{
					if (t.getPassword().equals(password))
					{
						teacher = t;
						break;
					}
				}
				if (teacher == null)
				{
					System.out.println("Could not find teacher.");
					System.exit(0);
				}
				System.out.println("You have successfully logged in!");
				int choice = 0;
				Class tClass = choseTeacherClass(keyboard, teacher);
				while (choice == 0)
				{
					choice = teacher.menu(tClass);
					while (choice == 1)
					{
						tClass = choseTeacherClass(keyboard, teacher);
						choice = teacher.menu(tClass);
					}
					if (choice == -1)
					{
						return;
					}
				}
			}
		}
	}

	private static boolean teacherEmailIsValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@Teacher.edu";
		return email.matches(regex);
	}			




	public static void addStudent(StudentDatabase sd, Scanner keyboard)
	{

		System.out.println("Please Enter the first name of the student: ");
		String firstName = keyboard.next();
		System.out.println("Please Enter the last name of the student: ");
		String lastName = keyboard.next();
		Student newStudent = new Student(firstName, lastName);
		students.add(newStudent);

		sd.addToList(newStudent.getStudentEmail());
		sd.addPassword(newStudent.getPassword());
		sd.addStudent(newStudent);

		System.out.println("Which class do you want to regester for? \n" );
		for(Class c: classes) 
			System.out.println(c.toString());
		int num = keyboard.nextInt();
		boolean a = regesteringStudent(newStudent, num);
		while(a == false) {
			System.out.println("Enter a valid CRN:  \n" + classes.toString());
			num= keyboard.nextInt();
			a = regesteringStudent(newStudent, num);
		}
	}

	private static boolean regesteringStudent(Student newStudent, int num) {
		for(Class c : classes) {
			if(c.getCRN()== num) {
				c.addStudent(newStudent);
				newStudent.addClass(c);
				System.out.println("You just regestered for class: " + num);
				return true;
			}
		}
		return false;
	}


	public static void addTeacher(TeacherDatabase td, Scanner keyboard)
	{
		System.out.println("Please Enter the first name of the Teacher: ");
		String firstName = keyboard.next();
		System.out.println("Please Enter the last name of the Teacher: ");
		String lastName = keyboard.next();
		Teacher newTeacher = new Teacher(firstName, lastName);
		teachers.add(newTeacher);
		teacherClasses(keyboard, newTeacher);

		td.addToList(newTeacher.getTeacherEmail());
		td.addPassword(newTeacher.getPassword());
		System.out.println();

	}

	private static void teacherClasses(Scanner keyboard, Teacher newTeacher)
	{

		System.out.print("Enter how many classes you teach: ");
		int numClasses = keyboard.nextInt();
		keyboard.nextLine();
		while (numClasses < 1)
		{
			System.out.println("A teacher must teach at least one class.");
			System.out.print("Enter how many classes you teach: ");
			numClasses = keyboard.nextInt();
		}
		while (numClasses > 10)
		{
			System.out.println("A teacher can't teach more than 10 classes.");
			System.out.print("Enter how many classes you teach: ");
			numClasses = keyboard.nextInt();
		}
		System.out.println("\nBE SURE TO ENTER A 4-DIGIT CRN CODE!\n");
		for (int i = 0; i < numClasses; i++)
		{
			System.out.print("CRN for class " + (i + 1) + ": ");
			String CRN = keyboard.nextLine();
			while (String.valueOf(CRN).length() != 4)
			{
				System.out.println("A CRN must have 4 digits!");
				System.out.print("Please enter a valid CRN code: ");
				CRN = keyboard.nextLine();
			}
			int crn = Integer.parseInt(CRN);
			newTeacher.addClass(crn);
			classes.add(new Class(crn, newTeacher));
		}
	}

	private static Class choseTeacherClass(Scanner keyboard, Teacher teacher)
	{

		System.out.print("Your classes are: ");
		int[] classNums = teacher.getClasses();
		int i = 0;
		while (i < classNums.length && classNums[i] != 0)
		{
			System.out.println(classNums[i]);
			i++;
		}
		System.out.print("Enter the number of the class you want to view: ");
		int num = keyboard.nextInt();
		Class tClass = null;
		for (Class c : classes)
		{
			if (c.getCRN() == num)
			{
				tClass = c;
			}
		}
		return tClass;
	}
}
