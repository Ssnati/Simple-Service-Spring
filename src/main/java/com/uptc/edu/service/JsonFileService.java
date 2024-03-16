package com.uptc.edu.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uptc.edu.models.Person;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonFileService {
    private final String filePath = "src/main/resources/persons.json";
    private final Gson gson;

    public JsonFileService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<Person> loadDataFromJson(){
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            FileReader fileReader = new FileReader(filePath);
            return List.of(gson.fromJson(fileReader, Person[].class));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file");
        }
    }

    public void saveDataToJson(List<Person> persons){
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            gson.toJson(persons, fileWriter);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error writing file");
        }
    }
}
