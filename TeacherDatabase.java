import java.util.ArrayList;

public class TeacherDatabase
    {
        // private Teacher teacher;
        private ArrayList<String> teacherEmails = new ArrayList<String>();
        private ArrayList<String> teacherPasswords = new ArrayList<String>();
        // private ArrayList<ArrayList<String>> teacherLogInInfo = new
        // ArrayList<ArrayList<String>>();
        private String[][] teacherLoginInfo = new String[10][2];
        private String teacherName;
        private Teacher teacher;
        String firstName;
        String lastName;

        public void addToList(String teacherEmail)
            {
                teacherEmails.add(teacherEmail);
                for (int x = 0; x < teacherEmails.size(); x++)
                    {

                        teacherLoginInfo[x][0] = teacherEmails.get(x);
                    }
            }

        public String listOfTeacherEmails()
            {
                StringBuilder str = new StringBuilder();
                for (int x = 0; x < teacherEmails.size(); x++)
                    {
                        str.append(teacherEmails.get(x) + "\n");

                    }

                return str.toString();
            }

        public void addPassword(String password)
            {
                teacherPasswords.add(password);

                for (int x = 0; x < teacherPasswords.size(); x++)
                    {
                        teacherLoginInfo[x][1] = teacherPasswords.get(x);
                    }
            }

        public String listOfTeacherPasswords()
            {
                StringBuilder str = new StringBuilder();
                for (String s : teacherPasswords)
                    {
                        str.append(s + "\n");
                    }
                return str.toString();

            }

        public void getLoginInfo()
            {

                for (int i = 0; i < teacherLoginInfo.length; i++)
                    {
                        for (int j = 0; (teacherLoginInfo != null && j < teacherLoginInfo[i].length); j++)
                            {
                                if (teacherLoginInfo[i][j] == null)
                                    {
                                        break;
                                    }
                                System.out.print(teacherLoginInfo[i][j] + "\t");
                            }

                        System.out.println();
                    }
            }
        


        public boolean validateLogin(String email, String password)
            {
                boolean emailAddr = false;
                boolean pass = false;

                for (int x = 0; x < teacherLoginInfo.length; x++)
                    {
                        if (teacherLoginInfo[x][0].equalsIgnoreCase(email))
                            {
                                emailAddr = true;
                                break;
                            }

                    }

                for (int x = 0; x < teacherLoginInfo.length; x++)
                    {
                		if(teacherLoginInfo[x][1] != null) {
                			if (teacherLoginInfo[x][1].equalsIgnoreCase(password))
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

            }

    }