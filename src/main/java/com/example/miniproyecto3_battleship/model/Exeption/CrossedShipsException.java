package com.example.miniproyecto3_battleship.model.Exeption;


public class CrossedShipsException extends Exception {

  public CrossedShipsException(){
    super();
  }

  public CrossedShipsException(String message) {
    super(message);
  }

  public CrossedShipsException(String message, Throwable cause) {
    super(message, cause);
  }

  public CrossedShipsException(Throwable cause) {
    super(cause);
  }


}
