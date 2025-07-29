package com.example.miniproyecto3_battleship.model.Exeption;

/**
 * Exception thrown when two or more ships overlap on the game board.
 * This exception is used to indicate an invalid game state where ship positions conflict.
 *
 * <p>This is a custom exception extending {@code Exception} to provide specific
 * error handling for overlapping ships in the Battleship game.</p>
 *
 * @author Juan David Lopez vanegas cod 2243077
 */

public class CrossedShipsException extends Exception {

  /**
   * Default constructor for the {@code CrossedShipsException}.
   * <p>Initializes the exception with no specific message or cause.</p>
   */

  public CrossedShipsException(){
    super();
  }

  /**
   * Constructs a {@code CrossedShipsException} with a specified detail message.
   *
   * @param message the detail message explaining the cause of the exception.
   */


  public CrossedShipsException(String message) {
    super(message);
  }

  /**
   * Constructs a {@code CrossedShipsException} with a specified detail message
   * and a cause.
   *
   * @param message the detail message explaining the cause of the exception.
   * @param cause the underlying cause of this exception, represented as a {@code Throwable}.
   */
  public CrossedShipsException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a {@code CrossedShipsException} with a specified cause.
   *
   * @param cause the underlying cause of this exception, represented as a {@code Throwable}.
   */
  public CrossedShipsException(Throwable cause) {
    super(cause);
  }


}
