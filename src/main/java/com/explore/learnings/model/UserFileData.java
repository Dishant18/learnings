package com.explore.learnings.model;

import java.util.List;
import java.util.Map;

public class UserFileData {
    private final Map<String, Integer> header;
    private final List<UserFileRecord> records;


    public UserFileData(Map<String, Integer> header, List<UserFileRecord> records) {
        this.header = header;
        this.records = records;
    }

    public Map<String, Integer> getHeader() {
        return header;
    }

    public List<UserFileRecord> getRecords() {
        return records;
    }
}
