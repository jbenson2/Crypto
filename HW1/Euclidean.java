
//Import scanner class to allow for user input
import java.util.Scanner;

public class Euclidean {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Setup scanner
        System.out.print("Enter two numbers separated by a space: ");
        Double num1 = scanner.nextDouble(); // Read the console for the first number
        Double num2 = scanner.nextDouble(); // Read the console for the second number
        System.out.println("gcd(" + num1 + ", " + num2 + ") = " + gcd(num1, num2)); // Compute gcd and return to console
        scanner.close(); // Close the scanner
    }

    // Recursive function to compute gcd using Euclideans algorithm
    static double gcd(double a, double b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}