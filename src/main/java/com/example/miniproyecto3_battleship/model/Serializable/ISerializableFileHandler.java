package com.example.miniproyecto3_battleship.model.Serializable;

import java.io.Serializable;

public interface ISerializableFileHandler {
    void serialize(String fileName, Object element);


    Object deserialize(String fileName);
}
