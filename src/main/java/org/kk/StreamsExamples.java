package org.kk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamsExamples {
    public static void main(String[] args) {

        /*Get unique characters from the list of words*/
        List<String> words = Arrays.asList("Hello","World");

        List<String> uniqueLetters = words.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        System.out.println(uniqueLetters);

        /*Get all pairs of numbers of both lists */
        List<Integer>  listA = Arrays.asList(1,2,3);
        List<Integer> listB = Arrays.asList(3,4);

        List<List<Integer>> pairs = listA.stream().flatMap(integer1 -> listB.stream().map(integer2 -> Arrays.asList(integer1, integer2))).collect(toList());
        System.out.println(pairs);

        /*Get only those pairs whose sum is divisible by 3*/
        // Approach 1 - Get all pairs and filter later
        List<List<Integer>> multiplesOf3Pairs = listA.stream().flatMap(integer1 -> listB.stream().map(integer2 -> Arrays.asList(integer1, integer2))).filter(integers -> ((integers.get(0) + integers.get(1)) % 3) == 0).collect(toList());
        System.out.println(multiplesOf3Pairs);

        //Approach 2 - Filter while getting them itself
        multiplesOf3Pairs = listA.stream().flatMap(integer1 -> listB.stream().filter(integer2 -> ((integer1 + integer2) % 3) == 0).map(integer2 -> Arrays.asList(integer1, integer2))).collect(toList());
        System.out.println(multiplesOf3Pairs);


        List<Integer> integers = Arrays.asList(3,8,10,17, 12,34,6);
        System.out.println(integers.stream().reduce(0,(a,b) -> {
            System.out.println("adding (a,b):" + a + "," + b);
            return a + b ;
        }));

        /*Lambda expression passed to reduce should be able to execute in any order*/
        System.out.println(integers.parallelStream().reduce(0,(a,b) -> {
            System.out.println("adding (a,b):" + a + "," + b);
            return a + b ;
        }));
    }


}
