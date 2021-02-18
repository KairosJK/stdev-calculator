import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

    public static void main(String[] args) {
        int manualOrFile;
        int[] userInputs = new int[100];

        manualOrFile = printOpening();

        if (manualOrFile == 1) {
            userInputs = manualScanner();
        } else if (manualOrFile == 2) {
            userInputs = fileScanner();
        }

        printResults(userInputs, meanCalc(userInputs), medianCalc(userInputs), modeCalc(userInputs), varianceCalc(meanCalc(userInputs), userInputs), stdevCalc(varianceCalc(meanCalc(userInputs), userInputs)));
        varianceCalc(meanCalc(userInputs), userInputs);
    }

    public static void printResults(int[] data, double mean, double median, int[] mode, double var, double stDev) {

        String cyan = "\u001B[36m";
        String blue = "\u001B[34m";

        System.out.print(blue + "════════════════════════════════════════════════════════════════════════════════");
        System.out.print(cyan + "\n The Dataset Provided: ");
        for (int i = 0; i < data.length - 1; i++) {
            System.out.print(data[i] + ", ");
        }
        System.out.print(data[data.length - 1]);

        System.out.print("\n The Mean: " + mean);
        System.out.print("\n The Median: " + median);
        System.out.print("\n The Mode: ");

        for (int i = 0; i < mode.length - 1; i++) {
            System.out.print(mode[i] + ", ");
        }
        System.out.print(mode[mode.length - 1]);

        System.out.print("\n The Variance: " + var);
        System.out.print("\n The Standard Deviance: " + stDev);
        System.out.print(blue + "\n═════════════════════════════════════════════════════════════════════════════");
    }

    public static int printOpening() {

        String cyan = "\u001B[36m";
        String blue = "\u001B[34m";
        Scanner manualOrFileScanner = new Scanner(System.in);
        String manualEntry = "manual-entry";
        String fileEntry = "file-entry";
        boolean checkCondition = true;

        while (checkCondition) {
            System.out.print(blue + "════════════════════════════════════════════════════════════════════════════════\n");
            System.out.print(cyan + "Please Select either \"manual-entry\" mode or \"file-entry\" mode\n");
            System.out.print(blue + "════════════════════════════════════════════════════════════════════════════════\n");
            String manualOrFile = manualOrFileScanner.nextLine();

            if (manualOrFile.equals(manualEntry)) {
                return 1;
            } else if (manualOrFile.equals(fileEntry)) {
                return 2;
            }
        }
        return 0;
    }

    public static int[] manualScanner() {
        Scanner manualScanner = new Scanner(System.in);
        String[] sortingArray = new String[100];
        int[] numberArray = new int[100];
        String cyan = "\u001B[36m";
        String blue = "\u001B[34m";

        boolean scannerCheckCondition = true;
        System.out.print(blue + "════════════════════════════════════════════════════════════════════════════════\n");
        System.out.println(cyan + "Please input every numeral sequentially (NOTE: This input method only supports 100 Datapoints)");
        System.out.println(cyan + "Type \"done\" to finish inputting data");
        System.out.print(blue + "════════════════════════════════════════════════════════════════════════════════\n");

        int i = 0;
        int k = 0;
        while (scannerCheckCondition) {
            sortingArray[i] = manualScanner.nextLine();

            if (sortingArray[i].matches("[0-9]+")) {
                numberArray[k] = Integer.parseInt(sortingArray[i]);
                k++;
            } else if (sortingArray[i].equals("done")) {
                break;
            } else {
                i++;
            }
        }
        return numberArray;
    }

    public static int[] fileScanner() {
        String cyan = "\u001B[36m";
        String blue = "\u001B[34m";
        Scanner fileScanner = new Scanner(System.in);
        String fileName;

        System.out.print(blue + "════════════════════════════════════════════════════════════════════════════════\n");
        System.out.println(cyan + "Please input the name of the file (You must include an absolute file path)");
        System.out.print(blue + "════════════════════════════════════════════════════════════════════════════════\n");
        fileName = fileScanner.nextLine();

        File userFile = new File(fileName);

        int count = 0;
        int[] numberArray = null;

        try {
            Scanner countNumbers = new Scanner(userFile);
            Scanner scanNumbers = new Scanner(userFile);
            while (countNumbers.hasNextInt()) {
                count++;
                countNumbers.next();
            }

            numberArray = new int[count];

            int i = 0;

            while (scanNumbers.hasNextInt()) {
                numberArray[i] = scanNumbers.nextInt();
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return numberArray;
    }

    public static double meanCalc(int[] collectedData) {
        double mean = 0;

        for (int i = 0; i < collectedData.length; i++) {
            mean = mean + collectedData[i];
        }

        mean = mean / collectedData.length;

        return mean;
    }

    public static double medianCalc(int[] collectedData) {
        double median = 0;
        int arrLength = collectedData.length;
        int double1 = 0;
        int double2 = 0;

        Arrays.sort(collectedData);

        if (collectedData.length % 2 == 1) {
            median = collectedData[(arrLength + 1) / 2 - 1];
        } else {
            double1 = collectedData[arrLength / 2 - 1];
            double2 = collectedData[arrLength / 2];
            median = ((double) double1 + double2) / 2;
        }
        return median;
    }

    public static int[] modeCalc(int[] collectedData) {

        int[] rawMode = new int[collectedData.length]; // Mode array, made an array to possibly store more than 1 mode.
        int topCount = 0; // Counts for the highest frequency in the dataset
        int modeIndex = 0; // Used as an index for the mode array.

        //Fills the mode array with -1 placeholders
        for (int i = 0; i < collectedData.length; i++) {
            rawMode[i] = -1;
        }

        Arrays.sort(collectedData); // Sorts the array smallest -> largest

        int count;
        for (int i = 0; i < collectedData.length; i++) {
            count = 0;
            for (int k = 0; k < collectedData.length; k++) {
                if (collectedData[k] == collectedData[i]) {
                    ++count;
                }
            }
            if (count > topCount) {
                topCount = count;
            }
        }

        for (int i = 0; i < collectedData.length; i++) {
            int counter = 0;
            for (int k = 0; k < collectedData.length; k++) {
                if (collectedData[k] == collectedData[i]) {
                    ++counter;
                }
            }
            if (counter == topCount) {
                rawMode[modeIndex] = collectedData[i];
                modeIndex++;
            }
        }

        int countdifference = 0;
        int current = rawMode[0];
        boolean found = false;
        int minusone = -1;

        for (int i = 0; i < rawMode.length; i++) {
            if (current == rawMode[i] && !found) {
                found = true;
            } else if (current != rawMode[i] && current != minusone) {
                countdifference++;
                current = rawMode[i];
                found = false;
            }
        }

        int[] mode = new int[countdifference];

        int index = 0;
        current = rawMode[0];
        found = false;

        for (int i = 0; i < rawMode.length; i++) {
            if (current == rawMode[i] && !found) {
                found = true;
            } else if (current != rawMode[i] && current != minusone) {
                mode[index] = current;
                current = rawMode[i];
                found = false;
                index++;
            }
        }

        return mode;
    }

    public static double varianceCalc(double mean, int[] collectedData) {

        double[] subData = new double[collectedData.length];

        for (int i = 0; i < collectedData.length; i++) {
            subData[i] = collectedData[i] - mean;
            subData[i] = subData[i] * subData[i];
        }

        double var = 0;
        for (int i = 0; i < subData.length; i++) {
            var = var + subData[i];
        }

        var = var / subData.length;

        return var;
    }

    public static double stdevCalc(double var) {
        double stDev = 0;
        stDev = Math.sqrt(var);
        return stDev;
    }
}


