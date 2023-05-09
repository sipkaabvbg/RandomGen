package com.random;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author ivanov
 * This class provides list of tests:
 * 1.Test empty arrays
 * 2.Test null arrays
 * 3.Test different size of arrays
 * 4.Test probabilities are not negative
 * 5.Test probabilities have NaN
 * 6.Test probabilities not greater than one
 * 7.Test probabilities total not 1
 * 8.Test always occurring probabilities with one
 */
public class RandomGenTest {

    private int[] randomNums;
    private float[] probabilities;

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyArrays() {
        randomNums = new int[0];
        probabilities = new float[0];
        new RandomGen(randomNums, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullArrays() {
        randomNums = null;
        probabilities = null;
        new RandomGen(randomNums, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDifferentSizeArrays() {
        randomNums = new int[]{1};
        probabilities = new float[]{0.2f, 0.1f};
        new RandomGen(randomNums, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProbabilitiesIsNotNegative() {
        randomNums = new int[]{1, 2, 3};
        probabilities = new float[]{0.2f, -0.1f, -0.4f};
        new RandomGen(randomNums, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProbabilitiesHasNaN() {
        randomNums = new int[]{1, 3};
        probabilities = new float[]{0.2f, Float.NaN};
        new RandomGen(randomNums, probabilities);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProbabilitiesNotGreaterThanOne() {
        randomNums = new int[]{1, 3};
        probabilities = new float[]{0.2f, 1.5f};
        new RandomGen(randomNums, probabilities);
    }

    @Test
    public void testProbabilitiesTotalNotOne() {
        int maxTryCall = 100;
        int nextNum;
        randomNums = new int[]{1, 2};
        probabilities = new float[]{0.2f, 0.8f};
       RandomGen randomGen= new RandomGen(randomNums, probabilities);
        for (int i = 0; i < maxTryCall; i++) {
            nextNum = randomGen.nextNum();
            // make sure we always get -1 which has prob 1
            Assert.assertEquals(nextNum, -1);
        }
    }

    @Test
    public void testAlwaysOccuringEventsProbabilitiesOne() {
        int maxTryCall = 100;
        int nextNum;
        randomNums = new int[]{2, -1, 3, 4, 5};
        probabilities = new float[]{0.0f, 1.0f, 0.0f, 0.0f, 0.0f};
        RandomGen randomGen = new RandomGen(randomNums, probabilities);

        for (int i = 0; i < maxTryCall; i++) {
            nextNum = randomGen.nextNum();
            // make sure we always get -1 which has prob 1
            Assert.assertEquals(nextNum, -1);
        }
    }
}