package edu.neu.khoury.cs5004.problem1;

import java.util.regex.Pattern;

/**
 * Parses a template to write a document.
 */
public interface ITemplateParser extends IParser{


  /**
   * @return Parsed file.
   * @throws Exception thrown if file has not yet been read.
   */
  @Override
  ParsedTemplate parse() throws Exception;

  /**
   * Validates the template string. If there are no key words in double brackets, an exception is
   * thrown.
   *
   * @param string Template string.
   * @throws Exception thrown if there are no key words.
   */
  static void validateTemplateString(String string) throws Exception {
    if (!Pattern.compile("\\[\\[\\w+]]").matcher(string).find()) {
      throw new IllegalArgumentException("Template has no key words.");
    }
  }

}
