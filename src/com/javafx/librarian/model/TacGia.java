package com.javafx.librarian.model;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TacGia {
    private IntegerProperty MaTacGia;
    private StringProperty TenTacGia;

    public int getMaTacGia() {
        return MaTacGia.get();
    }

    public IntegerProperty maTacGiaProperty() {
        return MaTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.MaTacGia.set(maTacGia);
    }

    public String getTenTacGia() {
        return TenTacGia.get();
    }

    public StringProperty tenTacGiaProperty() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.TenTacGia.set(tenTacGia);
    }

    public TacGia(){}

    public TacGia(int maTacGia, String tenTacGia) {
        MaTacGia = new SimpleIntegerProperty(maTacGia);
        TenTacGia = new SimpleStringProperty(tenTacGia);
    }

    @Override
    public String toString()
    {
        return this.getMaTacGia() + " - " + this.getTenTacGia();
    }
}
