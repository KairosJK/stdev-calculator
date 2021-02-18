public class operations {

    public static double meanCalc(int[] collectedData) {
        double mean = 0;

        for (int i = 0; i < collectedData.length; i++) {
            mean = mean + collectedData[i];
        }

        mean = mean / collectedData.length;

        return mean;
    }
}
