package com.example.miniproyecto3_battleship.model.Serializable;

import java.io.*;

/**
 * Handles serialization and deserialization of objects to and from files.
 *
 * <p>The {@code SerializableFileHandler} class provides methods for saving objects
 * to files in a serialized format and retrieving them later. It uses Java's standard
 * serialization mechanism.</p>
 *
 * <p>This class is useful for persisting game states or other serializable objects.</p>
 *
 * @author Juan David Lopez Vanegas
 */
public class SerializableFileHandler implements ISerializableFileHandler {
    @Override
    public void serialize(String fileName, Object element) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(element);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object deserialize(String fileName) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            return (Object) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}