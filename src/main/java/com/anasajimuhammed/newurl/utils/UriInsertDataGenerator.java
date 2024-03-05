package com.anasajimuhammed.newurl.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class UriInsertDataGenerator {

    public static void main(String[] args) {
        String fileName = "E:\\JavaFullStack\\new-url\\newUrlMicroservice\\new-url\\urls.txt";
        String outputFileName = "E:\\JavaFullStack\\new-url\\newUrlMicroservice\\new-url\\formatted_urls.json";

        List<JSONObject> jsonObjects = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                JSONObject obj = new JSONObject();
                obj.put("originalURL", line);
                jsonObjects.add(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter file = new FileWriter(outputFileName)) {
            file.write(jsonObjects.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(jsonObjects);
    }
}

