package org.kk;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 200, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        List<String> lowCaloricDishNames =  getLowCaloricDishNames(menu);
        System.out.println(lowCaloricDishNames);

        List<String> lowCaloricDishNames8 =  getSortedLowCaloricDishNames8(menu);
        System.out.println(lowCaloricDishNames8);

        List<String> top3HighCalorificDishNames = menu.stream().filter(dish -> {
            System.out.println("Filtering dish: " + dish.getName());
            return dish.getCalories() > 300;
        }).map(dish -> {
            System.out.println("Mapping dish: " + dish.getName());
            return dish.getName();
        }).limit(3).collect(Collectors.toList());

        System.out.println(top3HighCalorificDishNames);

        menu.stream().filter(dish -> {
            System.out.println("Filtering dish: " + dish.getName());
            return dish.getCalories() > 300;
        }).map(dish -> {
            System.out.println("Mapping dish: " + dish.getName());
            return dish.getName();
        }).limit(3).forEach(name -> {
            System.out.println("Printing dishName: " + name);
        });

        menu.stream().map(Dish::getCalories).reduce(0,Integer::sum);

    }

    /*Using Java 8 approach*/
    private static List<String> getSortedLowCaloricDishNames8(List<Dish> dishes) {
        return dishes.stream().filter(dish -> dish.getCalories() < 400).sorted(Comparator.comparing(Dish::getCalories)).map(Dish::getName).collect(Collectors.toList());

    }

    /*Using Java 7 approach*/
    private static List<String> getLowCaloricDishNames(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if(dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(),o2.getCalories());
            }
        });

        List<String> dishNames = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            dishNames.add(dish.getName());
        }
        return dishNames;
    }

}

class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "calories=" + calories +
                ", name='" + name + '\'' +
                '}';
    }

    public enum Type { MEAT, FISH, OTHER }
}
