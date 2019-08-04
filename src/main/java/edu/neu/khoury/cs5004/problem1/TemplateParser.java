package edu.neu.khoury.cs5004.problem1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a template.
 */
public class TemplateParser implements ITemplateParser {
  private String templatePath;

  /**
   * Constructor for a TemplateParser.
   *
   * @param templatePath File path to template.
   */
  public TemplateParser(String templatePath) {
    this.templatePath = templatePath;
  }

  /**
   * Creates a string of the text file.
   *
   * @param path File path to text file.
   * @return Text file as a string.
   * @throws Exception thrown if file cannot be read.
   */
  private String buildStringFromFile(String path) throws Exception {
    BufferedReader bufferedTemplateReader = new BufferedReader(new InputStreamReader(new
        FileInputStream(path), StandardCharsets.UTF_8));
    StringBuilder templateStringBuilder = new StringBuilder();
    String line;
    if ((line = bufferedTemplateReader.readLine()) != null) {
      templateStringBuilder.append(line);
    }
    while ((line = bufferedTemplateReader.readLine()) != null) {
      templateStringBuilder.append(System.lineSeparator());
      templateStringBuilder.append(line);
    }
    bufferedTemplateReader.close();
    return templateStringBuilder.toString();
  }

  /**
   * Reads the Template in as a string.
   *
   * @return Template as a string.
   * @throws Exception thrown if file path is not valid.
   */
  private String read() throws Exception {
    IParser.validateFile(templatePath);
    return buildStringFromFile(templatePath);
  }

  /**
   * Parses the fields enclosed in double brackets - [["field"]] - in the template string.
   *
   * @param templateString Template as a string.
   * @return Set of fields in template.
   * @throws Exception thrown if file is not yet read, or if the template has no fields.
   */
  private Set<String> parseFields(String templateString) throws Exception {
    IParser.ensureFileIsRead(templateString);
    ITemplateParser.validateTemplateString(templateString);
    Set<String> fields = new HashSet<>();
    Matcher matcher = Pattern.compile("\\[\\[(\\w+)]]")
        .matcher(templateString);
    while (matcher.find()) {
      fields.add(matcher.group(1));
    }
    return fields;
  }

  /**
   * @return Parsed file.
   * @throws Exception thrown if file has not yet been read.
   */
  @Override
  public ParsedTemplate parse() throws Exception {
    String templateString = read();
    return new ParsedTemplate(templateString, parseFields(templateString));
  }

}
