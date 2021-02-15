import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int manualOrFile;
        int[] userInputs = new int[100];

        manualOrFile = introDialogue();

        if (manualOrFile == 1) {
            userInputs = manualScanner();
        } else if (manualOrFile == 2) {
            userInputs = fileScanner();
        }

        System.out.print("\nThe Mean is: " + meanCalc(userInputs));
        System.out.print("\nThe Median is: " + medianCalc(userInputs));
    }

    public static int introDialogue() {
        Scanner manualOrFileScanner = new Scanner(System.in);
        String manualEntry = "manual-entry";
        String fileEntry = "file-entry";
        boolean checkCondition = true;

        while (checkCondition) {
            System.out.print("Please Select either \"manual-entry\" mode or \"file-entry\" mode\n");
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

        boolean scannerCheckCondition = true;
        System.out.println("Please input every numeral sequentially (NOTE: This input method only supports 100 Datapoints)");
        System.out.println("Type \"done\" to finish inputting data");

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
        Scanner fileScanner = new Scanner(System.in);
        String fileName;

        System.out.println("Please input the name of the file (You must include an absolute file path)");
        fileName = fileScanner.nextLine();

        File userFile = new File(fileName);

        int count = 0;
        int[] numberArray = null;

        try {
            Scanner countNumbers = new Scanner(userFile);
            Scanner scanNumbers = new Scanner(userFile);
            while (countNumbers.hasNextInt()) {
                count++;
                System.out.println("found " + count + " datapoint(/s)");
                countNumbers.next();
            }

            System.out.println("Found " + count + " datapoints in total.");
            numberArray = new int[count];

            int i = 0;

            while (scanNumbers.hasNextInt()) {
                numberArray[i] = scanNumbers.nextInt();
                i++;
            }

            for (int k = 0; k < numberArray.length; k++) {
                System.out.print(numberArray[k]);
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

    /*public static int[] modeCalc(int[] collectedData) {

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

        for (int i = 0; i> rawMode.length; i++){

        }

        int[] mode = new int[];

        return mode;
    }*/
}

    /*public double varianceCalc(){
        return;
    }*/

    /*public double stdevCalc(){

    }*/
