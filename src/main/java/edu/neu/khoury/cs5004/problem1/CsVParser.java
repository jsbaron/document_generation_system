package edu.neu.khoury.cs5004.problem1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A CSV Parser class.
 * CSV data is required to be between double quotes.
 * Null values must be represented in the CSV as double quotes with nothing between them.
 */
public class CsVParser implements ICsVParser {

  private String path;
  private Set<String> fields;
  private BufferedReader csvReader;

  /**
   * Constructor for a CSV Parser with specified columns.
   *
   * @param path   CSV file path.
   * @param fields Names of columns to parse.
   */
  public CsVParser(String path, Set<String> fields) {
    this.path = path;
    this.fields = fields;
  }

  /**
   * Constructor for a CSV parser to parse all columns.
   * Allows for a CSV parser outside of the context of document generation from a template.
   *
   * @param path File path to CSV.
   */
  public CsVParser(String path) {
    this.path = path;
    this.fields = null;
  }

  /**
   * Reads the file.
   *
   * @throws Exception if file is not a valid path.
   */
  public void read() throws Exception {
    IParser.validateFile(path);
    this.csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(path),
        StandardCharsets.UTF_8));
  }

  /**
   * @return Parsed file.
   * @throws Exception thrown if file has not yet been read.
   */
  @Override
  public ParsedCsV parse() throws Exception {
    read();
    IParser.ensureFileIsRead(csvReader);
    ParsedCsV parsedCsV = new ParsedCsV(parseCsV());
    csvReader.close();
    return parsedCsV;
  }

  /**
   * Parses the CSV.
   *
   * @return A Map of column names to a List of data for each row in that column.
   * @throws Exception thrown if requested fields are not in the CSV.
   */
  private Map<String, List<String>> parseCsV() throws Exception {
    Map<Integer, String> headerMap = parseHeaders();
    return parseData(headerMap);
  }

  /**
   * Parses the data after headers have been parsed.
   *
   * @param headerMap A map of column indices to column names.
   * @return A map of column names to a list of data for each row.
   * @throws Exception thrown if CSV cannot be read.
   */
  private Map<String, List<String>> parseData(Map<Integer, String> headerMap) throws Exception {
    String line;
    Map<String, List<String>> dataMap = createDataMap();
    while ((line = csvReader.readLine()) != null) {
      List<String> row = parseRow(line);
      for (Integer column : headerMap.keySet()) {
        dataMap.get(headerMap.get(column)).add(row.get(column));
      }
    }
    return dataMap;
  }

  /**
   * Creates an empty map of desired fields to an empty list.
   *
   * @return Map of desired fields to an empty list of strings.
   */
  private Map<String, List<String>> createDataMap() {
    Map<String, List<String>> dataMap = new HashMap<>();
    for (String field : fields) {
      dataMap.put(field, new ArrayList<>());
    }
    return dataMap;
  }

  /**
   * Splits a row into a List.
   *
   * @param row A line read in from the CSV file.
   * @return Line split into a list, not cleaned yet.
   */
  private List<String> splitRow(String row) {
    return Arrays.asList(row.split("\",\""));
  }

  /**
   * Removes trailing and leading quotes from first and last column values.
   *
   * @param row List of values.
   */
  private void removeTrailingQuotesFirstAndLast(List<String> row) {
    row.set(0, row.get(0).substring(1));
    String lastCol = row.get(row.size() - 1);
    row.set(row.size() - 1, lastCol.substring(0, lastCol.length() - 1));
  }

  /**
   * Parses a row in the CSV.
   *
   * @param row A line read in from the CSV.
   * @return Parsed row.
   */
  private List<String> parseRow(String row) {
    List<String> parsedRow = splitRow(row);
    removeTrailingQuotesFirstAndLast(parsedRow);
    return parsedRow;
  }

  /**
   * Validates that the fields requested are in the CSV headers.
   *
   * @param headers Collection of column names.
   * @throws Exception thrown if the requested field is not in the CSV.
   */
  private void validateHeadersMatchFields(Collection<String> headers) throws Exception {
    for (String col : fields) {
      if (!headers.contains(col)) {
        throw new IllegalArgumentException("Template does not match CSV headers.");
      }
    }
  }

  /**
   * Gets the index of column for each requested column name.
   *
   * @param headers List of column names.
   * @return Map of column indices to column names.
   */
  private Map<Integer, String> getIndicesOfFields(List<String> headers) {
    Map<Integer, String> indexMap = new HashMap<>();
    for (int i = 0; i < headers.size(); i++) {
      if (fields.contains(headers.get(i))) {
        indexMap.put(i, headers.get(i));
      }
    }
    return indexMap;
  }

  /**
   * Parses the headers of a CSV file.
   * If no fields were specified. Each column name is set to the fields to be parsed.
   *
   * @return Map of column indices to column names.
   * @throws Exception thrown if requested fields are not in the CSV.
   */
  private Map<Integer, String> parseHeaders() throws Exception {
    List<String> headers = parseRow(csvReader.readLine());
    if (fields == null) {
      this.fields = new HashSet<>(headers);
    }
    validateHeadersMatchFields(headers);
    return getIndicesOfFields(headers);
  }

}
