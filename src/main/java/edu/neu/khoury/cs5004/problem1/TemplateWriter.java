package edu.neu.khoury.cs5004.problem1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * A class to handle all writing of Templates.
 */
public class TemplateWriter implements ITemplateWriter {

  ParsedCsV parsedCsv;
  ParsedTemplate parsedTemplate;
  String outPutDirectory;
  String templateType;


  /**
   * A constructor for a TemplateWriter object.
   * @param parsedCsv parsedCsv
   * @param parsedTemplate parsedTemplate
   * @param outPutDirectory an output Dir
   * @param templateType the type of template to write
   */
  public TemplateWriter(ParsedCsV parsedCsv,
      ParsedTemplate parsedTemplate, String outPutDirectory, String templateType) {
    this.parsedCsv = parsedCsv;
    this.parsedTemplate = parsedTemplate;
    this.outPutDirectory = outPutDirectory;
    this.templateType = templateType;
  }

  /**
   * Populates a template with a list from an index.
   *
   * @param ind index
   * @return populatedTemplate String
   */
  public String populateTemplate(Integer ind) {
    String populatedTemplate = this.parsedTemplate.getTemplateString();
    for (String field : this.parsedTemplate.getFields()) {

      populatedTemplate = populatedTemplate
          .replaceAll("\\[\\[" + field + "]]", this.parsedCsv.getMap().get(field).get(ind));
    }
    return populatedTemplate;

  }


  /**
   * Writes autopopulated templates to a batch of files.
   * @throws IOException IOException
   */
  public void writeToFile() throws IOException {
    Integer row;
    Integer number;
    File file;
    for (row = 0; row < this.parsedCsv.getNumRows(); row++) {
      number = row + 1;
      if (this.templateType.equals("--email-template")) {
        file = new File(outPutDirectory + "emails" + number.toString());
      } else {
        file = new File(outPutDirectory + "letter" + number.toString());
      }

      PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file),
          StandardCharsets.UTF_8));
      printWriter.println(this.populateTemplate(row));
      printWriter.close();
    }


  }


}
