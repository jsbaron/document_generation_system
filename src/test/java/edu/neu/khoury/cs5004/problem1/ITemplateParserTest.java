package edu.neu.khoury.cs5004.problem1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ITemplateParserTest {

  @Test(expected = Exception.class)
  public void validateTemplateStringExc() throws Exception {
    ITemplateParser.validateTemplateString("I am not valid.");
  }

  @Test
  public void validateTemplateString() throws Exception {
    ITemplateParser.validateTemplateString("I [[am]] valid.");
  }
}