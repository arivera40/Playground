package RiverPackage;

import java.util.ArrayList;
import java.util.Random;

public class RiverManager {

    static Random rand = new Random();

    public int[][] generateRiver(int dim, double p) {
        int[][] river = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if ((i == 0 && j == 0) || (i == dim - 1 && j == dim - 1))
                    continue; // Skip if start or goal state
                if (rand.nextDouble() <= p)
                    river[i][j] = 1; // 1 - represents obstacle
            }
        }
        return river;
    }

    public void printRiver(int[][] river) {
        int dim = river.length;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (j == dim - 1) {
                    System.out.println(river[i][j]);
                } else {
                    System.out.print(river[i][j] + "\t");
                }
            }
        }
    }

    public ArrayList<Integer> riverLengths(int[][] river) {
        int[][] traveled = new int[river.length][river[0].length];
        ArrayList<Integer> riverLengths = new ArrayList<>();
        int totalLength = 0;

        for (int i = 0; i < river.length; i++) {
            for (int j = 0; j < river[0].length; j++) {
                if (traveled[i][j] == 1 || river[i][j] == 0) {
                    continue;
                }

                totalLength = 1;
                traveled[i][j] = 1;

                // Check adjacent river top
                if (j - 1 >= 0 && river[i][j - 1] == 1) {
                    traveled[i][j - 1] = 1;
                    totalLength += adjacentRiver(river, traveled, i, j - 1);
                } 
                // Check adjacent river right
                if (i + 1 < river.length && river[i + 1][j] == 1) {
                    traveled[i + 1][j] = 1;
                    totalLength += adjacentRiver(river, traveled, i + 1, j);
                    
                } 
                // Check adjacent river bottom
                if (j + 1 < river[0].length && river[i][j + 1] == 1) {
                    traveled[i][j + 1] = 1;
                    totalLength += adjacentRiver(river, traveled, i, j + 1);
                    
                } 
                // Check adjacent river left
                if (i - 1 >= 0 && river[i - 1][j] == 1) {
                    traveled[i - 1][j] = 1;
                    totalLength += adjacentRiver(river, traveled, i - 1, j);
                }
                
                if (totalLength > 1) {
                    riverLengths.add(totalLength);
                    totalLength = 0;
                }
            }
        }

        return riverLengths;
    }

    public int adjacentRiver(int[][] river, int[][] traveled, int i, int j) {
        int totalLength = 1;
        // Check adjacent river top
        if (j - 1 >= 0 && traveled[i][j - 1] != 1 && river[i][j - 1] == 1) {
            traveled[i][j - 1] = 1;
            totalLength += adjacentRiver(river, traveled, i, j - 1);
        } 
        // Check adjacent river right
        if (i + 1 < river.length && traveled[i + 1][j] != 1 && river[i + 1][j] == 1) {
            traveled[i + 1][j] = 1;
            totalLength += adjacentRiver(river, traveled, i + 1, j);
        } 
        // Check adjacent river bottom
        if (j + 1 < river[0].length && traveled[i][j + 1] != 1 && river[i][j + 1] == 1) {
            traveled[i][j + 1] = 1;
            totalLength += adjacentRiver(river, traveled, i, j + 1);
        } 
        // Check adjacent river left
        if (i - 1 >= 0 && traveled[i - 1][j] != 1 && river[i - 1][j] == 1) {
            traveled[i - 1][j] = 1;
            totalLength += adjacentRiver(river, traveled, i - 1, j);
        }

        return totalLength;
    }
}