package com.example.miniproyecto3_battleship.model.Serializable;

import java.io.Serializable;

/**
 * Interface for handling the serialization and deserialization of objects to and from files.
 *
 * <p>The {@code ISerializableFileHandler} interface defines methods for saving objects
 * in a serialized format and reading them back from files. Implementing classes should
 * provide the functionality for serializing and deserializing objects.</p>
 *
 * @author Juan David Lopez Vanegas
 */
public interface ISerializableFileHandler {

    /**
     * Serializes an object and saves it to a file.
     *
     * <p>This method writes the given object to the specified file in a binary format
     * using an {@code ObjectOutputStream}.</p>
     *
     * @param fileName the name of the file to save the serialized object to.
     * @param element the object to be serialized and saved.
     */
    void serialize(String fileName, Object element);


    /**
     * Deserializes an object from a file.
     *
     * <p>This method reads an object from the specified file and returns it.
     * If the file does not exist or deserialization fails, the method returns {@code null}.</p>
     *
     * @param fileName the name of the file to read the serialized object from.
     * @return the deserialized object, or {@code null} if an error occurs.
     */
    Object deserialize(String fileName);
}
