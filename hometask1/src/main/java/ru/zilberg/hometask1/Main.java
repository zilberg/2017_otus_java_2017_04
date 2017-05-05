package ru.zilberg.hometask1;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println("My firs hometask Otus Java 2017 04");

        String pathToFile;

        if (args.length >= 1){
            pathToFile = args[0];
        }else {
            pathToFile = "emails.csv";
        }

        System.out.println("Reading mails from: " + pathToFile);

        CSVReader reader = new CSVReader(new FileReader(pathToFile));

        List <String> emails = reader.readAll().stream().map(line -> line[0].trim()).collect(Collectors.toList());

        System.out.println("Emails count:" + emails.size());

        emails.forEach(System.out::println);

    }









}
