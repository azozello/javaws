package com.cassoviacode.javaws.payload;

import java.util.ArrayList;
import java.util.List;

public class PayloadList<T> {

    private List<T> records;

    public PayloadList() {
        this.records = new ArrayList<>();
    }

    public PayloadList(List<T> records) {
        this.records = records;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
