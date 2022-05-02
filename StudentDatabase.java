import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentDatabase
    {

        private ArrayList<String> studentEmails = new ArrayList<String>();
        private ArrayList<String> studentPasswords = new ArrayList<String>();
        private String[][] studentLoginInfo = new String[10][2];
        private ArrayList<Student> students = new ArrayList();
        private String conn;

        public void addStudent(Student s)
            {
                students.add(s);
            }

        public void printStudents()
            {
                StringBuilder str = new StringBuilder();
                for (int x = 0; x < students.size(); x++)
                    {
                        str.append("ID: " + students.get(x).getID() + "\t" + "Name: " + students.get(x).getFirstName() + " " +
                                students.get(x).getLastName() + "\t" + "Email: " + students.get(x).getStudentEmail());
                    }
            }

        public ArrayList<Student> getStudents()
            {
                return students;
            }

        public void addToList(String studentEmail)
            {
                studentEmails.add(studentEmail);
                for (int x = 0; x < studentEmails.size(); x++)
                    {

                        studentLoginInfo[x][0] = studentEmails.get(x);
                    }
            }

        public String listOfStudentEmails()
            {
                StringBuilder str = new StringBuilder();
                for (int x = 0; x < studentEmails.size(); x++)
                    {
                        str.append(studentEmails.get(x) + "\n");

                    }
                return str.toString();
            }

        public void addPassword(String password)
            {
                studentPasswords.add(password);

                for (int x = 0; x < studentPasswords.size(); x++)
                    {
                        studentLoginInfo[x][1] = studentPasswords.get(x);
                        
                    }
            }

        public String listOfStudentPasswords()
            {
                StringBuilder str = new StringBuilder();
                for (String s : studentPasswords)
                    {
                        str.append(s + "\n");
                    }
                return str.toString();

            }

        /*
         * public String[][] getLogInArray() { return studentLoginInfo; }
         */

        public void getLoginInfo()
            {

                for (int i = 0; i < studentLoginInfo.length; i++)
                    {
                        for (int j = 0; (studentLoginInfo != null && j < studentLoginInfo[i].length); j++)
                            {
                                if (studentLoginInfo[i][j] == null)
                                    {
                                        break;
                                    }
                                System.out.print(studentLoginInfo[i][j] + "\t");
                            }

                        System.out.println();
                    }
            }

        public boolean validateLogin(String email, String password)
            {
                boolean emailAddr = false;
                boolean pass = false;

                for (int x = 0; x < studentLoginInfo.length; x++)
                    {
                        if (studentLoginInfo[x][0].equalsIgnoreCase(email))
                            {
                                emailAddr = true;
                                break;
                            }

                    }

                for (int x = 0; x < studentLoginInfo.length; x++)
                    {
                		if(studentLoginInfo[x][1] != null) {
                			if (studentLoginInfo[x][1].equals(password)) //y throw exception
                            	{
                					pass = true;
                					break;
                            	}
                		}

                    }

                if (emailAddr && pass)
                    {
                        return true;
                    } else
                    {
                        return false;
                    }
                //return true;

            }

    }