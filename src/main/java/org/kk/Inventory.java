package org.kk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Inventory {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(50, "Red"));
        inventory.add(new Apple(120, "Red"));
        inventory.add(new Apple(100, "Red"));
        inventory.add(new Apple(150, "Red"));
        inventory.add(new Apple(60, "Red"));
        inventory.add(new Apple(50, "Green"));
        inventory.add(new Apple(120, "Green"));
        inventory.add(new Apple(100, "Green"));
        inventory.add(new Apple(150, "Green"));
        inventory.add(new Apple(60, "Green"));
        inventory.add(new Apple(160, "Green"));

        /*Filtering using Behavior strategy pattern*/
        ApplePredicate predicate = new RedHeavyApplePredicate();
        System.out.println(filterApples(inventory, predicate));

        /*Replacing with Lambda */
        System.out.println(filter(inventory,
                (Apple apple) -> "Red".equals(apple.color) && apple.getWeight() >= 150));

        /*Lambdas can be assigned to Functional Interface variables - Here it can be assigned to either of App.Predicate  or  java.util.function.Predicate*/
        Predicate<Apple> appleFilter = (apple) -> "Red".equals(apple.color) && apple.getWeight() >= 150;
        System.out.println(filter(inventory,appleFilter));

        /*Sorting*/
        Comparator<Apple> appleComparator = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        };
        inventory.sort(appleComparator);

        /*appleComparatorLambda (Anonymous function) is concise representation of appleComparator (Anonymous class) using Lambda*/
        Comparator<Apple> appleComparatorLambda = (Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight());

        inventory.sort(appleComparatorLambda);

        /*Sorting by passing Lambda expression directly*/
        inventory.sort(((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight())));

        /*Using method reference*/
        inventory.sort(Comparator.comparing(Apple::getWeight));

        System.out.println(inventory);

    }

    @FunctionalInterface
    interface Predicate<T> {
        boolean test(T t);
    }

    static <T> List<T> filter(List<T> tList, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : tList) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    static List<Apple> filterApples(List<Apple> apples, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}


interface ApplePredicate {
    boolean test(Apple apple);
}

class GreenApplePredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "Green".equals(apple.color) ? true : false;
    }
}

class RedApplePredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "Red".equals(apple.color) ? true : false;
    }
}

class RedHeavyApplePredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "Red".equals(apple.color) && apple.getWeight() >= 150? true : false;
    }
}


