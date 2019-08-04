package edu.neu.khoury.cs5004.problem1;


import java.io.IOException;

/**
 * A TemplateWriter Interface.
 */
public interface ITemplateWriter {

  /**
   * Populates a template with a list from an index.
   *
   * @param index index
   * @return populatedTemplate String
   */
  String populateTemplate(Integer index);


  /**
   * Writes autopopulated templates to a batch of files.
   *
   * @throws IOException IOException
   */
  void writeToFile() throws IOException;


}
