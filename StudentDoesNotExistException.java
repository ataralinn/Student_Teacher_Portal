public class StudentDoesNotExistException extends Exception
    {

        static final long serialVersionUID = 1L;

        public StudentDoesNotExistException(String message)
            {
                super(message);
            }

        public StudentDoesNotExistException()
            {
                super("Student is not in this class");
            }
    }