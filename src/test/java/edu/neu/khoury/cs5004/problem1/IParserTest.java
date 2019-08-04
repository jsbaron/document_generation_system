package edu.neu.khoury.cs5004.problem1;

import org.junit.Test;


public class IParserTest {


  @Test(expected = Exception.class)
  public void ensureFileIsReadExc() throws Exception {
    IParser.ensureFileIsRead(null);
  }

  @Test
  public void ensureFileIsRead() throws Exception {
    IParser.ensureFileIsRead("A file");
  }
}