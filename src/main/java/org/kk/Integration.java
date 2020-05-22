package org.kk;

import java.util.function.DoubleFunction;


public class Integration {

    public static void main(String[] args) {
        System.out.println(integral((x) -> x+10, 3, 7));
    }

     static double integral(DoubleFunction<Double> f, double x1, double x2){
        return ((f.apply(x1) + f.apply(x2)) * (x2 - x1))/2 ;
    }
}
