package com.random;


/**
 * @author ivanov
 * This class implements helper methods
 */
public class RandomUtils {

    public static boolean isEqual(float val1, float val2) {
        return Float.compare(val1, val2) == 0;
    }

    public static boolean isLess(float val1, float val2) {
        boolean result = val1 < val2;
        if (isEqual(val1, val2)) {
            result = false;
        }
        return result;
    }

    public static boolean isGreater(float val1, float val2) {
        boolean result = val1 > val2;
        if (isEqual(val1, val2)) {
            result = false;
        }
        return result;
    }

    public static boolean checkAnyIsNanOrInfinite(float val) {
        return Float.isNaN(val) || Float.isInfinite(val);
    }
}