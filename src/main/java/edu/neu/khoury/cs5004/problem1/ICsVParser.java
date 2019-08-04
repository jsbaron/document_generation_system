package edu.neu.khoury.cs5004.problem1;

/**
 * Parses a CSV.
 */
public interface ICsVParser extends IParser {

  /**
   * @return Parsed file.
   * @throws Exception thrown if file has not yet been read.
   */
  @Override
  ParsedCsV parse() throws Exception;

}
