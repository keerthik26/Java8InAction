package org.kk;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConstructorReference {

    public static void main(String[] args) {
        List<Integer> weights = Arrays.asList(5, 10, 15, 20);
        map(weights);
        /*Same is done using constructor reference*/
        map(weights, Apple::new);

        /*Another example of Constructor reference - method to create different types of fruits using a single method */
        Map<String,Function<Integer,Fruit>> fruitsMap = new HashMap<>();
        fruitsMap.put("orange",Orange::new);
        fruitsMap.put("banana",Banana::new);
        fruitsMap.put("papaya",Papaya::new);

        giveMeFruit(fruitsMap,"orange", 30);

    }

    static Fruit giveMeFruit(Map<String,Function<Integer,Fruit>> fruitsMap, String name, Integer weight){
        return fruitsMap.get(name).apply(weight);
    }

    /*Maps weights to apples*/
    static List<Apple> map(List<Integer> weights){
        List<Apple> apples = new ArrayList<>();
        for (Integer weight : weights) {
            apples.add(new Apple(weight,"Red"));
        }
        return apples;
    }

    /*Maps weights to apples using BiFunction*/
    static List<Apple> map(List<Integer> weights, BiFunction<Integer, String, Apple> appleBiFunction){
        List<Apple> apples = new ArrayList<>();
        for (Integer weight : weights) {
            apples.add(appleBiFunction.apply(weight,"Red"));
        }
        return apples;
    }


}


class Fruit {
    private Integer weight;

    public Fruit(Integer weight) {
        this.weight = weight;
    }
}

class Orange extends  Fruit {

    public Orange(Integer weight) {
        super(weight);
    }
}

class Banana extends  Fruit {

    public Banana(Integer weight) {
        super(weight);
    }
}

class Papaya extends  Fruit {

    public Papaya(Integer weight) {
        super(weight);
    }
}