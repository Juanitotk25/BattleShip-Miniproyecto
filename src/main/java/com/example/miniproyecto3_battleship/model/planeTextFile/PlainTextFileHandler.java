package com.example.miniproyecto3_battleship.model.planeTextFile;

import java.io.*;


/**
 * Handles file operations for plain text files, including writing text to a file
 * and reading text from a file. Implements the {@code IPlaneTextFileHandler} interface.
 *
 * <p>This class provides utilities to persist and retrieve textual data from files,
 * with automatic resource management using try-with-resources.</p>
 *
 * @author Juan David Lopez Vanegas
 */

public class PlainTextFileHandler implements IPlaneTextFileHandler {

    @Override
    public void writeToFile(String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public String[] readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                content.append(line).append(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString().split(",");
    }
}


