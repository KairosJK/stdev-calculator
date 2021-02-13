import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int manualOrFile;
        int[] userinputs = new int[100];

        manualOrFile = introDialogue();

        if (manualOrFile == 1) {
            userinputs = manualScanner();
        } else if (manualOrFile == 2) {
            userinputs = fileScanner();
        }


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
}