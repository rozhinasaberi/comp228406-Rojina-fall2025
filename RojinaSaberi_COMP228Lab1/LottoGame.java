
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Arrays;


public class LottoGame {
    public static void main(String[] args) {
        Integer target = UserTarget();
        if (target == null) {
            JOptionPane.showMessageDialog(null, "Game cancelled!");
            return;
        }

        boolean userWon = false;
        for (int attempt = 1; attempt <= 5; attempt++) {
            Lotto lotto = new Lotto();
            int[] nums = lotto.getNumbers();
            int sum = nums[0] + nums[1] + nums[2];

            JOptionPane.showMessageDialog( null, "Roll " + attempt + " of 5\n" +"Numbers: " + Arrays.toString(nums) + "\n" +"Sum: " + sum);

            if (sum == target) {
                JOptionPane.showMessageDialog(null, "You matched! You Win!");
                userWon = true;
                break;
            }
        }

        if (!userWon) {
            JOptionPane.showMessageDialog(null, "No match. Computer wins!");
        }
    }

    private static Integer UserTarget() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Pick a number between 3 and 27:", "Lotto", JOptionPane.QUESTION_MESSAGE);
            if (input == null) return null;

            try {
                int n = Integer.parseInt(input); 
                if (n >= 3 && n <= 27) return n;
                JOptionPane.showMessageDialog(null, "Try again.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid whole number.");
            }
        }
    }
    }


    class Lotto {
    private final int[] numbers = new int[3];
    private static final Random x = new Random();

    public Lotto() {
        for (int i = 0; i < 3; i++) {
            numbers[i] = x.nextInt(9) + 1;
        }
    }

    
    public int[] getNumbers() {
        return numbers;
    }
}
