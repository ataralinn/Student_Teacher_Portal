import java.awt.Desktop;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Teacher extends Person
    {
        private String[] id;
        private String teacherEmail;
        private char[] password;
        private static ArrayList<String> arrayOfTeacherEmails = new ArrayList<String>();
        private int[] classNumbers;
        private Scanner keyboard;

        public Teacher(String firstName, String lastName)
            {
                super(firstName, lastName);
                setTeacherEmail();
                classNumbers = new int[10];
                keyboard = new Scanner(System.in);
            }


        public void addClass(int classNum)
            {
                int i = 0;
                while (classNumbers[i] != 0 && i < classNumbers.length)
                    i++;
                if (i != classNumbers.length)
                    classNumbers[i] = classNum;
                else
                    throw new ArrayIndexOutOfBoundsException("Can't add another class - exceeds class limit.");
            }

        public int[] getClasses()
            {
                return classNumbers;
            }

        public void setTeacherEmail()
            {
                char firstLetter = super.getFirstName().charAt(0);
                this.teacherEmail = Character.toString(firstLetter) + getLastName() + "@Teacher.edu";
                arrayOfTeacherEmails.add(teacherEmail);
                // addTeacherEmailToArray(teacherEmail);
                System.out.println("This is your teacher Email: " + getTeacherEmail());

            }


        public String getTeacherEmail()
            {
                return teacherEmail;
            }

        /*
         * public void addTeacherEmailToArray(String teacherEmail) {
         * arrayOfTeacherEmails.add(teacherEmail); }
         */

        public void setID()
            {
                Random random = new Random();
                id = new String[10];
                for (int x = 0; x <= 3; x++)
                    {
                        id[x] = String.valueOf(0);
                    }
                for (int x = 3; x <= id.length; x++)
                    {
                        id[x] = String.valueOf(random.nextInt());
                    }
            }


        public void addTeacherEmailToArray(String teacherEmail)
            {
                Random random = new Random();
                arrayOfTeacherEmails.add(teacherEmail);
                for (int x = 0; x <= 2; x++)
                    {
                        id[x] = String.valueOf(0);
                    }
                for (int x = 3; x < id.length; x++)
                    {
                        id[x] = String.valueOf(random.nextInt(9 - 0) + 0);
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

        public String getListOfTeacherEmails()
            {
                if (arrayOfTeacherEmails.size() != 0)
                    {
                        for (String email : arrayOfTeacherEmails)
                            {
                                return email;
                            }
                    } else
                    {
                        return ("There are no emails");
                    }
                return null;

            }


        /**
         * This method returns the professor's menu
         *
         * @return the professor's menu
         */
        public int menu(Class tClass)
            {
                System.out.println("Teacher Menu: ");
                System.out.println("Enter '1' to post an assignment");
                System.out.println("Enter '2' to view assignments and students' progress");
                System.out.println("Enter '3' to post a grade");
                System.out.println("Enter '4' to view a different class");
                System.out.println("Enter '5' to exit");
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
                            postAssignment(tClass);
                            break;
                        case 2:
                            viewAssignments(tClass);
                            break;
                        case 3:
                            postGrade(tClass);
                            break;
                        case 4:
                            return 1;
                        case 5:
                            return -1;
                    }
                return 0;
            }

        private void postGrade(Class tClass)
            {
                char again = 'y';
                do
                    {
                        viewAssignments(tClass);
                        System.out.print("Enter the number of the assignment you would like to grade: ");
                        int assignment = keyboard.nextInt();
                        ArrayList<Student> classList = tClass.getStudents();
                        int max = classList.size();
                        for (int i = 0; i < max; i++)
                            {
                                System.out.println((i + 1) + ": " + classList.get(i).toString());
                                i++;
                            }
                        System.out.print("Enter the ID of the student whose assignment you want to grade: ");
                        int studentNum = keyboard.nextInt();
                        System.out.print("Enter the grade: ");
                        int grade = keyboard.nextInt();
                        keyboard.nextLine();
                        tClass.gradeAssignment(assignment - 1, studentNum , grade);
                        System.out.println("Assignment graded successfully!");
                        System.out.print("Enter another grade? (y/n) ");
                        again = keyboard.nextLine().toLowerCase().charAt(0);
                    } while (again == 'y');
            }

        private void viewAssignments(Class tClass)
            {
                System.out.println(tClass.getNumSubmittedAssignments());
            }

        private void postAssignment(Class tClass)
            {
                System.out.print("Assignment title: ");
                String title = keyboard.nextLine();
                System.out.print("Due date(in yyyy-mm-dd format): ");

                String dueDate = null;
                boolean illegal = false;
                LocalDate due = null;
                do
                    {
                        dueDate = keyboard.nextLine();
                        try
                            {
                                due = LocalDate.parse(dueDate);
                                illegal = false;
                            } catch (DateTimeParseException e)
                            {
                                illegal = true;
                                System.out.print("The format is illegal. Please enter a date with a valid format: ");
                            }
                    } while (illegal);
                System.out.print("Total points allowed for assignment: ");
                int points = keyboard.nextInt();
                keyboard.nextLine();
                System.out.print("Instructions: ");
                String instructions = keyboard.nextLine();
                tClass.addAssignment(new Assignment(title, due, points, instructions));
                System.out.println("Assignment has been added succesfully!");
            }

        public String getPassword()
            {
                return super.getPassword();
            }

    }
