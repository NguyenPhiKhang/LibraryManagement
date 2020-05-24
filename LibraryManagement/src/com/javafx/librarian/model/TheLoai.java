package com.javafx.librarian.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TheLoai {
    private IntegerProperty MaTheLoai;
    private StringProperty TenTheLoai;

    public int getMaTheLoai() {
        return MaTheLoai.get();
    }

    public IntegerProperty maTheLoaiProperty() {
        return MaTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.MaTheLoai.set(maTheLoai);
    }

    public String getTenTheLoai() {
        return TenTheLoai.get();
    }

    public StringProperty tenTheLoaiProperty() {
        return TenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.TenTheLoai.set(tenTheLoai);
    }

    public TheLoai(int maTheLoai, String tenTheLoai) {
        MaTheLoai = new SimpleIntegerProperty(maTheLoai);
        TenTheLoai = new SimpleStringProperty(tenTheLoai);
    }
}
