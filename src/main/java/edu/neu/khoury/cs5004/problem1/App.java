package edu.neu.khoury.cs5004.problem1;

/**
 * Class to run the Document Generation Application.
 */
public class App {

  /**
   * @param args Command line arguments.
   * @throws Exception thrown if arguments are invalid.
   */
  public static void main(String[] args) throws Exception {
    AbstractArgHandler.validate(args);
    Hub hub = AbstractArgHandler.read(args);
    hub.process();
  }

}
