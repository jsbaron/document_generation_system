package edu.neu.khoury.cs5004.problem1;

import java.util.Set;

/**
 * Class for encapsulating document generation app.
 */
public class Hub implements IHub {

  protected String templateDirectory;
  protected String outputDirectory;
  protected String csvFile;
  protected String templateType;

  /**
   * Constructor for a Hub.
   *
   * @param templateDirectory Directory for the template.
   * @param outputDirectory   Directory for the output files.
   * @param csvFile           Directory for the CSV file.
   * @param templateType      Type of the template.
   */
  public Hub(String templateDirectory, String outputDirectory, String csvFile,
             String templateType) {
    this.templateDirectory = templateDirectory;
    this.outputDirectory = outputDirectory;
    this.csvFile = csvFile;
    this.templateType = templateType;
  }


  /**
   * Processes information in an IHub.
   */
  @Override
  public void process() throws Exception {

    ParsedTemplate parsedTemplate = parseTemplate();

    ParsedCsV parsedCsV = parseCsV(parsedTemplate.getFields());


    TemplateWriter templateWriter = new TemplateWriter(parsedCsV, parsedTemplate,
        this.outputDirectory, this.templateType);

    templateWriter.writeToFile();
  }

  /**
   * @return A parsed template.
   * @throws Exception thrown if template is invalid.
   */
  private ParsedTemplate parseTemplate() throws Exception {
    TemplateParser templateParser = new TemplateParser(templateDirectory);
    return templateParser.parse();
  }

  /**
   * @param fields Set of fields to parse from the CSV.
   * @return A parsed CSV.
   * @throws Exception thrown if CSV headers don't match the template fields.
   */
  private ParsedCsV parseCsV(Set<String> fields) throws Exception {
    ICsVParser csvParser = new CsVParser(csvFile, fields);
    return csvParser.parse();
  }
}
