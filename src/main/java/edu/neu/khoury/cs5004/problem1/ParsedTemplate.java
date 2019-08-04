package edu.neu.khoury.cs5004.problem1;

import java.util.Set;

/**
 * A POJO to encapsulate data parsed from a template.
 */
public class ParsedTemplate {
  private String templateString;
  private Set<String> fields;

  /**
   * Constructor for a ParsedTemplate.
   *
   * @param templateString Template as a string.
   * @param fields Set of fields in the parsed template.
   */
  public ParsedTemplate(String templateString, Set<String> fields) {
    this.templateString = templateString;
    this.fields = fields;
  }

  /**
   * @return template string.
   */
  public String getTemplateString() {
    return templateString;
  }

  /**
   * @return fields in parsed template.
   */
  public Set<String> getFields() {
    return fields;
  }
}
