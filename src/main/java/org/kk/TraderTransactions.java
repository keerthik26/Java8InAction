package org.kk;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class TraderTransactions {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println(transactions.stream().filter(transaction -> transaction.getYear()==2011).sorted(comparing(Transaction::getValue)).collect(toList()));
        System.out.println(transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(toList()));
        System.out.println(transactions.stream().map(Transaction::getTrader).distinct().filter(trader -> trader.getCity().equals("Cambridge")).sorted(comparing(Trader::getName)).collect(toList()));
        System.out.println(transactions.stream().map(transaction -> transaction.getTrader().getName()).distinct().sorted().reduce("", (s, s2) -> s.concat(" ").concat(s2)));
        System.out.println(transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan")));
        System.out.println(transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).collect(toList()));
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(0,Integer::max));

        System.out.println(transactions.stream().reduce((transaction, transaction2) -> transaction.getValue() < transaction2.getValue() ? transaction : transaction2));
        Optional<Transaction> minTransaction = transactions.stream().min(comparing(Transaction::getValue));
        if(minTransaction.isPresent()){
            System.out.println(minTransaction.get());
        }
        //transactions.stream().sorted(comparing(Transaction::getValue)).findFirst().ifPresent(System.out::println);
        transactions.stream().collect(groupingBy(Transaction::getYear));

    }
}

class Trader{

    private final String name;
    private final String city;

    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }

    public String getName(){
        return this.name;
    }

    public String getCity(){
        return this.city;
    }

    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}

class Transaction{

    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader(){
        return this.trader;
    }

    public int getYear(){
        return this.year;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}