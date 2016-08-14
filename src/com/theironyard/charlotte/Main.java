package com.theironyard.charlotte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        HashMap<String, ArrayList<Person>> People = new HashMap<>();

        File f = new File ("people.csv");

        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()){

            String line = scanner.nextLine();
            String[] columns = line.split(",");
            Person peopleColumns = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            People.putIfAbsent(columns[4], new ArrayList<>());
            People.get(columns[4]).add(peopleColumns);
            Collections.sort(People.get(columns[4]));
            System.out.println(People);




        }
    }
}