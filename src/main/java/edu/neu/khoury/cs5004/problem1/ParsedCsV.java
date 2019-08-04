package edu.neu.khoury.cs5004.problem1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class for a read-only Parsed CSV file.
 */
public class ParsedCsV implements IParsedCsV {

  private final Map<String, List<String>> map;
  private final Integer numRows;
  private final Integer numColumns;
  private final Set<String> columnNames;

  /**
   * @param map A map of column names to each row of data.
   */
  public ParsedCsV(Map<String, List<String>> map) {
    this.map = Collections.unmodifiableMap(map);
    this.numRows = map.get(new ArrayList<>(map.keySet()).get(0)).size();
    this.numColumns = map.keySet().size();
    this.columnNames = map.keySet();
  }

  /**
   * @return A map of column names to a list of data in each row.
   */
  @Override
  public Map<String, List<String>> getMap() {
    return map;
  }

  /**
   * @return Number of rows in the CSV.
   */
  @Override
  public Integer getNumRows() {
    return numRows;
  }

  /**
   * @return Number of columns in the CSV.
   */
  @Override
  public Integer getNumColumns() {
    return numColumns;
  }

  /**
   * @return Set of column  names in the CSV.
   */
  @Override
  public Set<String> getColumnNames() {
    return columnNames;
  }
}
