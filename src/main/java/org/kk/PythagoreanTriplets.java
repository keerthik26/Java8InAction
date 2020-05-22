package org.kk;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagoreanTriplets {

    public static void main(String[] args) {

        Stream<int[]> triplets = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt((a * a) + (b * b)) % 1.0 == 0).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        triplets.forEach(triplet -> System.out.println(triplet[0] + ", " + triplet[1] + ", " + triplet[2]));

    }
}
