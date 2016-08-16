package com.theironyard.charlotte;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<String, ArrayList<Person>> people = new HashMap<>();

        File f = new File("people.csv");
        Scanner scanner = new Scanner(f);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] columns = line.split(",");

            Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            people.putIfAbsent(person.getCountry(), new ArrayList<>());
            people.get(person.getCountry()).add(person);
            Collections.sort(people.get(person.getCountry()));
        }

        System.out.println(people);

        File nf = new File("person.json");
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(people);
        FileWriter fw = new FileWriter(nf);
        fw.write(json);
        fw.close();

        Scanner s = new Scanner(nf);
        s.useDelimiter("\\Z");
        String contents = s.next();
        JsonParser parser = new JsonParser();
        Person p = parser.parse(contents, Person.class);

        System.out.println(p);
    }
}