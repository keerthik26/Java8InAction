package org.kk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessorLambda {

    public static void main(String[] args) {
        try {
            //JAVA 7 approach
            System.out.println(processFile());
            //JAVA 8 approach - same method is used with 2 different behaviors below
            System.out.println(processFile((BufferedReader br) -> br.readLine()));
            System.out.println(processFile((BufferedReader br) -> br.readLine() + " " + br.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Method to read line from a file*/
    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return br.readLine();
        }
    }

    /*Parameterized the behavior for the above method using Functional Interface  - BufferedReaderProcessor*/
    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return processor.process(br);
        }
    }

    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    /*This is an another way by using an existing Functional Interface fom JAVA 8 API*/
   /* public static String processFile(Function<BufferedReader,String> processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return processor.apply(br);
        }
    }*/
}
