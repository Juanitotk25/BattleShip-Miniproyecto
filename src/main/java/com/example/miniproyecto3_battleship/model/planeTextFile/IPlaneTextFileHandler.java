package com.example.miniproyecto3_battleship.model.planeTextFile;

import java.io.IOException;

public interface IPlaneTextFileHandler {

    void writeToFile(String fileName, String text);

    String[] readFromFile(String fileName);
}
