package com.theironyard.charlotte;

/*
Create a Person class to store all the columns in the csv file.
Create a HashMap<String, ArrayList<Person>> that maps country name to a list of people from that country.
Loop over the HashMap and sort each list by last name.
Override toString in the Person class to print out a nicely-formatted string for that person (something like "Martha Jenkins from France").
Print out the entire HashMap at the end.
Break your code into separate methods.
Write the resulting HashMap to a file called "people.json" formatted as JSON.
 */

import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

            // write json
            JsonSerializer serializer = new JsonSerializer();
            String json = serializer.serialize(People);
            try {
                File nw = new File("person.json");
                FileWriter fw = new FileWriter(json);
                fw.write(json);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }
}