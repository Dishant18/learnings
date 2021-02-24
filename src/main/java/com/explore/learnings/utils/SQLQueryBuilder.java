package com.explore.learnings.utils;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class SQLQueryBuilder {

    public static String insertQuery(String tableName, List<String> cols) {
        return "INSERT INTO " +  tableName + "(" + String.join(",", cols) + ")" + " VALUES (" +
                cols.stream().map(col -> ":" + col).collect(Collectors.joining(",")) + ")";
    }

    public static String selectQuery(String tableName, List<String> cols, List<String> comparisonCols, boolean all) {
        String baseQuery = "SELECT " + (all ? "*" : String.join(",", cols)) + " FROM " + tableName;
        if (isNull(comparisonCols) || comparisonCols.isEmpty()) return baseQuery;
        return baseQuery + " WHERE " + comparisonCols.stream().map(col -> col + "=:" + col).collect(Collectors.joining(" AND "));
    }

    public static String upsertQuery(String tableName, List<String> cols, List<String> updateCols) {
        return insertQuery(tableName, cols) + " ON DUPLICATE KEY UPDATE " +
                updateCols.stream().map(col -> col + "=:" + col).collect(Collectors.joining(","));
    }

}
