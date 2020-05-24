package com.javafx.librarian.model;

import javafx.beans.property.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class NhanVien {
    private IntegerProperty MaNV;
    private StringProperty TenNV;
    private StringProperty DiaChi;
    private ObjectProperty<Date> NgaySinh;
    private StringProperty Email;
    private StringProperty SDT;

    public NhanVien(int maNV, String tenNV, String diaChi,
                    Date ngaySinh, String email, String sdt) {
        MaNV = new SimpleIntegerProperty(maNV);
        TenNV = new SimpleStringProperty(tenNV);
        DiaChi = new SimpleStringProperty(diaChi);
        NgaySinh = new SimpleObjectProperty<Date>(ngaySinh);
        Email = new SimpleStringProperty(email);
        SDT = new SimpleStringProperty(sdt);
    }

    public int getMaNV() {
        return MaNV.get();
    }

    public IntegerProperty maNVProperty() {
        return MaNV;
    }

    public String getTenNV() {
        return TenNV.get();
    }

    public StringProperty tenNVProperty() {
        return TenNV;
    }

    public String getDiaChi() {
        return DiaChi.get();
    }

    public StringProperty diaChiProperty() {
        return DiaChi;
    }

    public Date getNgaySinh() {
        return NgaySinh.get();
    }

    public ObjectProperty<Date> ngaySinhProperty() {
        return NgaySinh;
    }

    public String getEmail() {
        return Email.get();
    }

    public StringProperty emailProperty() {
        return Email;
    }

    public String getSDT() {
        return SDT.get();
    }

    public StringProperty SDTProperty() {
        return SDT;
    }
}
