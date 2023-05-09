package com.random;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author ivanov
 * This class implements nextNum method
 */
public class RandomGen {

    Logger logger = Logger.getLogger(RandomGen.class.getName());
    private int nonZeroProbElements = 0;
    // Values that may be returned by nextNum()
    private int[] randomNums;
    // Probability of the occurence of randomNums
    private float[] probabilities;
    //Cumulative Probabilities
    private float[] cumulativeProbabilities;
    private Random random;

    public RandomGen(int[] randomNums, float[] probabilities) {
        validateInputArrays(randomNums, probabilities);
        initCumulativeProbabilities(probabilities);
        random = new Random();
        this.cumulativeProbabilities = Arrays.copyOf(cumulativeProbabilities, nonZeroProbElements);
        this.randomNums = randomNums;
        this.probabilities = probabilities;

    }

    private void validateInputArrays(int[] randomNums, float[] probabilities) {
        if (randomNums == null || probabilities == null || randomNums.length != probabilities.length || probabilities.length == 0) {
            throw new IllegalArgumentException("The arrays randomNums and probabilities must be non empty and same size");
        }
    }

    private void initCumulativeProbabilities(float[] probabilities) {
        int probLength;
        float probability;

        probLength = probabilities.length;
        cumulativeProbabilities = new float[probLength];

        for (int i = 0; i < probLength; i++) {
            probability = probabilities[i];
            if (RandomUtils.isLess(probability, 0.0f)) {
                throw new IllegalArgumentException("Probability can't be negative");
            }
            if (RandomUtils.isGreater(probability, 1.0f)) {
                throw new IllegalArgumentException("Probability can't be greater than 1");
            }
            if (RandomUtils.checkAnyIsNanOrInfinite(probability)) {
                throw new IllegalArgumentException("Nan or Infite prob is not accepted");
            }
            // not processing elements with zero probabilities
            if (!RandomUtils.isEqual(probability, 0.0f)) {
                // store valid random number
                if (nonZeroProbElements == 0) {
                    cumulativeProbabilities[nonZeroProbElements] = probability;
                } else {
                    cumulativeProbabilities[nonZeroProbElements] = probability + cumulativeProbabilities[nonZeroProbElements - 1];
                }
                nonZeroProbElements++;
            }
        }
        if (!RandomUtils.isEqual(cumulativeProbabilities[nonZeroProbElements - 1], 1.0f)) {
            throw new IllegalArgumentException("Total probability must be 1");
        }
        logger.info("RandomNums :" + Arrays.toString(this.randomNums));
        logger.info("probabilities :" + Arrays.toString(this.probabilities));
        logger.info("nonZeroProbElement:" + nonZeroProbElements);
        logger.info("cumulativeProbabilities :" + Arrays.toString(cumulativeProbabilities));
    }

    /**
     * Returns one of the randomNums. When this method is called
     * multiple times over a long period, it should return the
     * numbers roughly with the initialized probabilities.
     */
    public int nextNum() {
        float nextFloat = random.nextFloat();
        return Arrays.binarySearch(cumulativeProbabilities, nextFloat);
    }
}