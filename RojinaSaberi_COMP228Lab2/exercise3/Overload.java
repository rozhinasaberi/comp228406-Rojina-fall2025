package exercise3;

public class Overload {

    // 1) average of two int numbers
    public static double average(int a, int b) {
        return (a + b) / 2.0;
    }

    // 2) average of three doubles
    public static double average(double a, double b, double c) {
        return (a + b + c) / 3.0;
    }

    // 3) average of an int array
    public static double average(int[] nums) {
        long sum = 0;
        if (nums == null || nums.length == 0) {
           return  Double.NaN;
        }
        for (int n : nums) sum += n;
        return sum / (double) nums.length;
    }

    public static void main(String[] args) {
        double n1 = average(40, 50);                       // calls average(int,int)
        double n2 = average(12.5, 33.5, 4.0);                // calls average(double,double,double)
        double n3 = average(new int[] { 1,2,3,4,5,6,7,8,9,10 }); // calls average of numbers 
        
        System.out.println("Average of Integer Numbers = " + n1);
        System.out.println("Average of Double Numbers = " + n2);
        System.out.println("Average of Numbers in an array = " + n3);
    }
}
