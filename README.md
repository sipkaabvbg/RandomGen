
Implement the method nextNum() and a minimal but effective set of unit tests. 
 As a quick check, given Random Numbers are [-1, 0, 1, 2, 3] and Probabilities are [0.01, 0.3, 0.58, 0.1, 0.01] if we call nextNum() 100 times we may get the following results. As the results are random, these particular results are unlikely.

-1: 1 times
0: 22 times
1: 57 times
2: 20 times
3: 0 times
public class RandomGen {
 // Values that may be returned by nextNum()
 private int[] randomNums;
 // Probability of the occurence of randomNums
 private float[] probabilities;

 /**
 Returns one of the randomNums. When this method is called
 multiple times over a long period, it should return the
 numbers roughly with the initialized probabilities.
 */
 public int nextNum() {

 }
}


#  RandomGen
The class RandomGen provides implementation of nextNum() method. 
The class RandomUtils implements helper methods.
The class RandomGenTest provides list of tests:
 1.Test empty arrays
 2.Test null arrays
 3.Test different size of arrays
 4.Test probabilities are not negative
 5.Test probabilities have NaN
 6.Test probabilities not greater than 1
 7.Test probabilities total not 1
 8.Test always occurring probabilities with 1
 
## Development environment
Java 11 and Maven 3.6.3
## Build
Build with Maven command "mvn clean install"

