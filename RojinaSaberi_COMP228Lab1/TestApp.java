import javax.swing.JOptionPane;
import java.util.Random;

public class TestApp {
    public static void main(String[] args) {
        Test t = new Test();
        t.inputAnswer(); // start the test
    }
}

class Test {
    Question[] questions;    // list of all questions
    Random x = new Random(); // random number generator for messages
    int correctNum = 0;    // how many correct so far
    int incorrectNum = 0;  // how many wrong so far

    private static class Question {
        final String prompt;
        final String[] options; 
        final int correctIndex; 

        Question(String prompt, String[] options, int correctIndex) {
            
            this.prompt = prompt;
            this.options = options;
            this.correctIndex = correctIndex;
        }
    }
    public Test() {

        // Question 1
        String q1Text = "1) which one is a bigger number";
        String[] q1Options = { "257", "-233", "253", "-257" };
        int q1Correct = 0; //257
        Question q1 = new Question(q1Text, q1Options, q1Correct);

        // Question 2
        String q2Text = "2) what day in the first day of the week?";
        String[] q2Options = { "Friday", "Saturday", "Monday", "Sunday" };
        int q2Correct = 2; // Monday
        Question q2 = new Question(q2Text, q2Options, q2Correct);

        // Question 3
        String q3Text = "3) which programming language is this?";
        String[] q3Options = {"JAVA","PYTHON","Javascript","C++"};
        int q3Correct = 0; // Java
        Question q3 = new Question(q3Text, q3Options, q3Correct);

        // Question 4
        String q4Text = "4)what color is the sky?";
        String[] q4Options = {"Red", "Blue", "Brown","Purple"};
        int q4Correct = 1; //Blue
        Question q4 = new Question(q4Text, q4Options, q4Correct);

        // Question 5
        String q5Text = "5) how many days are in a week?";
        String[] q5Options = {"3", "5",  "7", "8"};
        int q5Correct = 2; //7
        Question q5 = new Question(q5Text, q5Options, q5Correct);

        // Question 6
        String q6Text = "6) Which month is after September?";
        String[] q6Options = { "November", "December", "June", "October" };
        int q6Correct = 3; //october
        Question q6 = new Question(q6Text, q6Options, q6Correct);

        // Save all questions into the array
        questions = new Question[] { q1, q2, q3, q4, q5, q6 };
    }

    public void inputAnswer() {
        JOptionPane.showMessageDialog(null,
                "Hello! Welcome to Test App.\n" +
                "Type your answer or Close to quit.");

        for (Question q : questions) {
            Integer userChoice = simulateQuestion(q); //
            if (userChoice == null) {
                int confirm = JOptionPane.showConfirmDialog(  //popup window
                        null,
                        "Do you want to end the test now?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION //tells Java: show two buttons: Yes and No.
                );
                if (confirm == JOptionPane.YES_OPTION) break;
                userChoice = simulateQuestion(q);
                if (userChoice == null) { 
                    userChoice = -1;   // not 0-3 , so count as incorrect 
                }
            }

            boolean isCorrect = checkAnswer(userChoice, q.correctIndex);
            String feedback = generateMessage(isCorrect);

            if (isCorrect) {
                correctNum++;
                JOptionPane.showMessageDialog(null, feedback); //middle of page 
            } else {
                incorrectNum++;
                char correctLetter = (char) ('A' + q.correctIndex); 
                JOptionPane.showMessageDialog(
                        null,
                        feedback + "\n The Correct answer is : " + correctLetter + " - " + q.options[q.correctIndex]
                );
            }
        }

        int total = correctNum + incorrectNum;
        double pcorrect ;

        if (total == 0) {
            pcorrect = 0.0;   // avoid division by zero
        } else {
            pcorrect = (100.0 * correctNum) / total; } // calculate percentage 

        JOptionPane.showMessageDialog(
                null,
                "Done!\n" +
                "Number of Correct answers:   " + correctNum + "\n" +
                "Number of Incorrect answers: " + incorrectNum + "\n" +
                String.format("Total Score:     %.1f%%", pcorrect)
        );
    }
    
    private Integer simulateQuestion(Question q) {
        String msg = q.prompt + "\n\n";
        Integer choice;
        for (int i = 0; i < 4; i++) {
            msg += (char)('A' + i) + ") " + q.options[i] + "\n"; 
            
        }
            
        String input = JOptionPane.showInputDialog(
        null, msg , "Multiple-Choice Question", JOptionPane.QUESTION_MESSAGE
            );

        if (input == null) {
        return null;
        }
        
        else {
            
        if (input.equals("A") || input.equals("a")) choice = 0;
        else if (input.equals("B")|| input.equals("b")) choice = 1;
        else if (input.equals("C")||input.equals("c")) choice = 2;
        else if (input.equals("D")||input.equals("d")) choice = 3;
        else choice = -1; // invalid input
        }
        
        return choice; // 0-3 
    }
    
    private boolean checkAnswer(int userAnswer, int correctIndex) {
        return userAnswer == correctIndex;
    }
   
    private String generateMessage(boolean correct) {
        int randomNumber = x.nextInt(4); // pick 0, 1, 2, or 3
       
    if (randomNumber == 0) {
        if (correct) {
            return "Excellent!";
        } else {
            return "No. Please try again.";
        }
    } else if (randomNumber == 1) {
        if (correct) {
            return "Good!";
        } else {
            return "Wrong. Try once more.";
        }
    } else if (randomNumber == 2) {
        if (correct) {
            return "Keep up the good work!";
        } else {
            return "Don't give up!";
        }
    } else { // randomNumber == 3
        if (correct) {
            return "Nice work!";
        } else {
            return "No. Keep trying...";
        }
    }
}
    
    
}
