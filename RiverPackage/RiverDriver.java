package RiverPackage;

import java.util.Scanner;

public class RiverDriver {

    static RiverManager manager = new RiverManager();

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Set dimension to create river");
        int dim = userInput.nextInt();

        System.out.println("Set probability of adjacent river");
        double p = userInput.nextDouble();
        int[][] river = manager.generateRiver(dim, p);
        manager.printRiver(river);
        System.out.println("Length of river is: " + manager.riverLengths(river));
    }
}