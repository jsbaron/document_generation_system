package edu.neu.khoury.cs5004.problem1;

import java.io.File;

/**
 * An interface for parsing a file.
 *
 * @param <T> A object to return after parsing.
 */
public interface IParser<T> {

  /**
   * Validates a given file path.
   *
   * @param path A file path.
   * @throws Exception thrown if file does not exist.
   */
  static void validateFile(String path) throws Exception {
    File file = new File(path);
    if (!file.isFile()) {
      throw new IllegalArgumentException("File: " + path + " does not exist.");
    }
  }

  /**
   * @param object Object that is the output of reading a file.
   * @throws Exception thrown if user tries to parse before reading.
   */
  static void ensureFileIsRead(Object object) throws Exception {
    if (object == null) {
      throw new IllegalStateException("File must be read before it is parsed.");
    }
  }

  /**
   * @return Parsed file.
   * @throws Exception thrown if file has not yet been read.
   */
  T parse() throws Exception;

}
