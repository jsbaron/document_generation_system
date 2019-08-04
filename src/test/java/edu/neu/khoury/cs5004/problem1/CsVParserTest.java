package edu.neu.khoury.cs5004.problem1;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CsVParserTest {

  ICsVParser parser;
  ICsVParser invalidParserPath;
  ICsVParser invalidParserFields;
  ICsVParser missingDataParser;

  @Before
  public void setUp() throws Exception {
    parser = new CsVParser("/Users/joshuabaron/Documents/NEU/cs5004/Team_repo_Josh_Baron_Eric_Egan/Assignment8/src/main/java/edu/neu/khoury/cs5004/problem1/insurance_company_members.csv",
        new HashSet<>(Arrays.asList("first_name","last_name","company_name")));
    invalidParserPath = new CsVParser("Team_repo_Josh_Baron_Eric_Egan/Assignment8/src/main/java/edu/neu/khoury/cs5004/problem1/insurance_company_members.csv",
        new HashSet<>(Arrays.asList("first_name","last_name","company_name")));
    invalidParserFields = new CsVParser("/Users/joshuabaron/Documents/NEU/cs5004/Team_repo_Josh_Baron_Eric_Egan/Assignment8/src/main/java/edu/neu/khoury/cs5004/problem1/insurance_company_members.csv",
        new HashSet<>(Arrays.asList("first_name","last_name","company_name", "Not a Header")));
    missingDataParser = new CsVParser("/Users/joshuabaron/Documents/NEU/cs5004/Team_repo_Josh_Baron_Eric_Egan/Assignment8/src/main/java/edu/neu/khoury/cs5004/problem1/some_missing_data.csv",
        new HashSet<>(Arrays.asList("first_name","last_name","company_name")));
  }

  @Test(expected = Exception.class)
  public void readInvalid1() throws Exception {
    invalidParserPath.parse();
  }

  @Test(expected = Exception.class)
  public void parseInvalidFields() throws Exception {
    invalidParserFields.parse();
  }

  @Test
  public void parseFirstName0() throws Exception {
    assertEquals("James", parser.parse().getMap().get("first_name").get(0));
  }

  @Test
  public void parseFirstName240() throws Exception {
    assertEquals("Rickie", parser.parse().getMap().get("first_name").get(238));
  }

  @Test
  public void parseFirstNameLastRow() throws Exception {
    Map<String, List<String>> result = parser.parse().getMap();
    assertEquals("Chauncey", result.get("first_name").get(result.get("first_name").size() - 1));
  }

  @Test
  public void parseLastName0() throws Exception {
    assertEquals("Butt", parser.parse().getMap().get("last_name").get(0));
  }

  @Test
  public void parseLastName240() throws Exception {
    assertEquals("Plumer", parser.parse().getMap().get("last_name").get(238));
  }

  @Test
  public void parseLastNameLastRow() throws Exception {
    Map<String, List<String>> result = parser.parse().getMap();
    assertEquals("Motley", result.get("last_name").get(result.get("last_name").size() - 1));
  }

  @Test
  public void parseCompany0() throws Exception {
    assertEquals("Benton, John B Jr", parser.parse().getMap().get("company_name").get(0));
  }

  @Test
  public void parseCompany240() throws Exception {
    assertEquals("Merrill Lynch", parser.parse().getMap().get("company_name").get(238));
  }

  @Test
  public void parseCompanyLastRow() throws Exception {
    Map<String, List<String>> result = parser.parse().getMap();
    assertEquals("Affiliated With Travelodge", result.get("company_name").get(result.get("company_name").size() - 1));
  }

  @Test
  public void parseSize() throws Exception {
    assertEquals(parser.parse().getMap().get("last_name").size(), 500);
  }

  @Test
  public void parseFileWithMissingData1() throws Exception {
    Map<String, List<String>> result = missingDataParser.parse().getMap();
    assertEquals("", result.get("company_name").get(47));
  }

  @Test
  public void parseFileWithMissingData2() throws Exception {
    Map<String, List<String>> result = missingDataParser.parse().getMap();
    assertEquals("", result.get("last_name").get(62));
  }

  @Test
  public void numRows() throws Exception {
    assertEquals(500, parser.parse().getNumRows(), 0);
  }

  @Test
  public void numColumns() throws Exception {
    assertEquals(3, parser.parse().getNumColumns(), 0);
  }

  @Test
  public void columnNames() throws Exception {
    assertEquals(new HashSet<>(Arrays.asList("first_name", "last_name", "company_name")), parser.parse().getColumnNames());
  }

  @Test(expected = Exception.class)
  public void tryToModifyParsedMap() throws Exception {
    ParsedCsV parsedCSV = parser.parse();
    parsedCSV.getMap().put("Hello", new ArrayList<>(Collections.singletonList("hello")));
  }
}