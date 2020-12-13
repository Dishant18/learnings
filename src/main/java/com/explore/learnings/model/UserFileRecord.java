package com.explore.learnings.model;

import java.util.List;
import java.util.Map;

public class UserFileRecord {
    private Map<String, Integer> mapping;
    private final List<String> values;
    private final long recordNumber;

    public UserFileRecord(Map<String, Integer> mapping, List<String> values, long recordNumber) {
        this.mapping = mapping;
        this.values = values;
        this.recordNumber = recordNumber;
    }

    public String get(final int i) {
        return values.get(i);
    }

    public String get(final String name) {
        if (mapping == null) {
            throw new IllegalStateException(
                    "No header mapping was specified, the record values can't be accessed by name");
        }
        final Integer index = mapping.get(name);
        if (index == null) {
            return "";
        }
        try {
            return values.get(index.intValue());
        } catch (final ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format(
                    "Index for header '%s' is %d but CSVRecord only has %d values!", name, index,
                    Integer.valueOf(values.size())));
        }
    }

    public List<String> getValues() {
        return values;
    }

    public long getRecordNumber() {
        return recordNumber;
    }

    public void setMapping(Map<String, Integer> mapping) {
        this.mapping = mapping;
    }
}
