package edu.neu.khoury.cs5004.problem1;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for a parsed CSV file.
 */
public interface IParsedCsV {

  /**
   * @return A map of column names to a list of data in each row.
   */
  Map<String, List<String>> getMap();

  /**
   * @return Number of rows in the CSV.
   */
  Integer getNumRows();

  /**
   * @return Number of columns in the CSV.
   */
  Integer getNumColumns();

  /**
   * @return Set of column  names in the CSV.
   */
  Set<String> getColumnNames();

}
