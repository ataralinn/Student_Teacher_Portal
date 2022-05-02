import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Class
    {
		private final int CLASS_SIZE = 20;
        private int CRN;
        private Teacher professor;
        private ArrayList<Student> classList;
        private ArrayList<Assignment> assignments;
        private ArrayList<int[]> grades;
        private ArrayList<boolean[]> submitted;
        private String name;
        private Scanner keyboard;
        
        public Class(int CRN) {
        	this.CRN= CRN;
            classList = new ArrayList<Student>(CLASS_SIZE);
            assignments = new ArrayList<>();
            grades = new ArrayList<>();
            submitted = new ArrayList<>();
            keyboard = new Scanner(System.in);
        }

        public Class(int CRN, Teacher professor)
            {
        		this(CRN);
                this.professor = professor;
            }


        public void addStudent(Student newStudent)
            {
                int i = 0;
                if(classList.isEmpty()) {
                	classList.add(newStudent);
                }
                else {
                if (classList.size() < CLASS_SIZE) {
                	classList.add(newStudent);
                }
                else
                    throw new ArrayIndexOutOfBoundsException("Can't add another Student - exceeds class limit.");
            }
            }
        public ArrayList<Student> getStudents()
            {
                return classList;
            }


        public int getCRN()
            {
                return CRN;
            }


        public void setCRN(int CRN)
            {
                this.CRN = CRN;
            }

        public void addAssignment(Assignment assign)
            {
                assignments.add(assign);
                //For each assignment created, there's a corresponding array set to the size of the class
                //to hold each student's grade for that assignment. The subscript of the assignment and the
                //array will be the same. ex: assignment 5's grades will be the fifth array in grades
                grades.add(new int[CLASS_SIZE]);
                submitted.add(new boolean[CLASS_SIZE]);
                
            }

        public String getAssignments()
            {
        		if(!assignments.isEmpty()) {
                StringBuilder str = new StringBuilder("These are your assignments: \n");
	                int max = assignments.size();
	                for (int i = 0; i < max; i++)
	                    {
	                        str.append((i + 1) + ": " + assignments.get(i).toString() + "\n\n");
	                    }
	                return str.toString();
        		}else {
        			return "No assignments";
        		}
            }
        
        public String getNumSubmittedAssignments() {
        	StringBuilder str = new StringBuilder("These are your assignments: \n");
                int max = assignments.size();
                System.out.println(max);
                for (int i = 0; i < max; i++)
                    {
                        str.append((i + 1) + ": " + assignments.get(i).toString() + "\n");
                        
                        int numSubmitted = 0;
                        boolean[] thisAssignment = submitted.get(i);
                        for(boolean b: thisAssignment) {
                        	if(b == true) numSubmitted ++;
                        }
                        
                        str.append(numSubmitted + " students submitted this assignment.");
                        
                    }
                return str.toString();
        }
        
        
        public void viewSubmitted(int assignment) {
        	boolean[] thisAssignment = submitted.get(assignment);
        	ArrayList<String> names = new ArrayList<>();
        	int[] studSubmitted = new int[CLASS_SIZE];
        	int ctr = 0;
        	//Making a list of the students who submitted 
        	for(int i = 0; i < thisAssignment.length; i++) {
        		if(thisAssignment[i] == true) {
        			names.add(classList.get(i).getFirstName() + " " + classList.get(i).getLastName());
        			studSubmitted[ctr] = i;
        			ctr++;
        		}
        	}
        	if(ctr != 0) {
        		System.out.println("These students submitted: ");
        		for(String s: names) {
        			System.out.println(s + "\n");
        		}
        	}
        }

        public void setTeacher(Teacher professor) {
        	this.professor= professor;
        }
        public String getTeacher()
            {
                return professor.getID();
            }


        public void gradeAssignment(int assignment, int student, int grade)
            {
        		String ID = String.valueOf(student);
        		//Find the subscript of the student
                for(int i = 0; i < classList.size(); i++) {
                	if(classList.get(i).getID().equals(ID)) {
                		student = i;
                		break;
                	}
                }
                grades.get(assignment)[student] = grade;

            }


        public String getName()
            {
                return name;
            }

        public int[] getGrades(String[] id)
            {
        	int studNum = findStudent(id[0]);
            int[] studentsGrades = new int[grades.size()];

            for (int i = 0; i < grades.size(); i++)
                {
                    studentsGrades[i] = grades.get(i)[studNum];
                }
            return studentsGrades;
            }
        
        public void submitAssignment(int assignmentNum, String[] id) {
        	int studNum = findStudent(id[0]);
        	submitted.get(assignmentNum)[studNum] = true;
        	System.out.println("Your assignment has been submitted");
        }

		private int findStudent(String id) {
			int studNum = -1;
        	for(int i = 0; i < classList.size(); i++) {
        		if(id.equals(classList.get(i).getID())) {
        			studNum = i;
        			break;
        		}
        	}
        	if(studNum < 0)
        		throw new IllegalArgumentException ("Student is not registered for this class");
			return studNum;
		}

        @Override
		public String toString() {
        	if(professor != null) {
        		return "Class [CRN=" + CRN + ", professor=" + professor.getFirstName()
					+ " " + professor.getLastName() + "]\n";
        	}else {
        		return "Class [CRN=" + CRN + ", professor: TBA ]\n";
        	}
			
		}
    }
