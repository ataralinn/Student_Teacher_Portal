import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Assignment
    {

        private String title;
        private LocalDate dueDate;
        private int points;
        private String instructions;

        public Assignment(String title, LocalDate dueDate, int points, String instructions)
            {
                this.title = title;
                this.dueDate = dueDate;
                this.points = points;
                this.instructions = instructions;

            }

        public Assignment(String title, String instructions)
            {
                this(title, null, 0, instructions);
            }

        public Assignment(LocalDate dueDate, int points)
            {
                this(null, dueDate, points, null);
            }

        public void setTitle(String title)
            {
                this.title = title;
            }

        public String getTitle()
            {
                return title;
            }

        public void setDueDate(LocalDate dueDate)
            {
                this.dueDate = dueDate;
            }

        public LocalDate getDueDate()
            {
                return dueDate;
            }

        public void setPoints(int points)
            {
                this.points = points;
            }

        public int getPoints()
            {
                return points;
            }

        public void setInstructions(String instructions)
            {
                this.instructions = instructions;
            }



        @Override
        public String toString()
            {
                StringBuilder str = new StringBuilder("Assignment title: " + title);
                str.append(", dueDate: " + dueDate + ", points: " + points + ", instructions: " + instructions);
                return str.toString();
            }


    }