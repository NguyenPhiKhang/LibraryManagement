package com.javafx.librarian.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class Sach {
    private IntegerProperty MaSach;
    private StringProperty TenSach;
    private IntegerProperty MaTheLoai;
    private IntegerProperty MaTacGia;
    private IntegerProperty NamXB;
    private StringProperty NXB;
    private ObjectProperty<LocalDate> NgayNhap;
    private IntegerProperty TriGia;
    private IntegerProperty TinhTrang;
    private StringProperty AnhBia;

    public int getMaSach() {
        return MaSach.get();
    }

    public IntegerProperty maSachProperty() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        this.MaSach.set(maSach);
    }

    public String getTenSach() { return TenSach.get();}

    public StringProperty tenSachProperty() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        this.TenSach.set(tenSach);
    }

    public int getMaTheLoai() {
        return MaTheLoai.get();
    }

    public IntegerProperty maTheLoaiProperty() {
        return MaTheLoai;
    }

    public void setMaTheLoai(int maTheLoai) {
        this.MaTheLoai.set(maTheLoai);
    }

    public int getMaTacGia() {
        return MaTacGia.get();
    }

    public IntegerProperty maTacGiaProperty() {
        return MaTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.MaTacGia.set(maTacGia);
    }

    public int getNamXB() {
        return NamXB.get();
    }

    public IntegerProperty namXBProperty() {
        return NamXB;
    }

    public void setNamXB(int namXB) {
        this.NamXB.set(namXB);
    }

    public String getNXB() { return NXB.get();}

    public StringProperty nxbProperty() {
        return NXB;
    }

    public void setNXB(String nxb) {
        this.NXB.set(nxb);
    }

    public LocalDate getNgayNhap() { return NgayNhap.get();}

    public ObjectProperty<LocalDate> ngayNhapProperty() { return NgayNhap;}

    public void setNgayNhap(LocalDate ngayNhap) { this.NgayNhap.set(ngayNhap);}

    public int getTriGia() {
        return TriGia.get();
    }

    public IntegerProperty triGiaProperty() {
        return TriGia;
    }

    public void setTriGia(int triGia) {
        this.TriGia.set(triGia);
    }

    public int getTinhTrang() {
        return TinhTrang.get();
    }

    public IntegerProperty tinhTrangProperty() {
        return TinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.TinhTrang.set(tinhTrang);
    }

    public String getAnhBia() { return AnhBia.get();}

    public StringProperty anhBiaProperty() {
        return AnhBia;
    }

    public void setAnhBia(String anhBia) {
        this.AnhBia.set(anhBia);
    }

    public Sach(int maSach, String tenSach, int maTheLoai, int maTacGia, int namXB, String nxb, LocalDate ngayNhap, int triGia, int tinhTrang, String anhBia) {
        MaSach = new SimpleIntegerProperty(maSach);
        TenSach = new SimpleStringProperty(tenSach);
        MaTheLoai = new SimpleIntegerProperty(maTheLoai);
        MaTacGia = new SimpleIntegerProperty(maTacGia);
        NamXB = new SimpleIntegerProperty(namXB);
        NXB = new SimpleStringProperty(nxb);
        NgayNhap = new SimpleObjectProperty<LocalDate>(ngayNhap);
        TriGia = new SimpleIntegerProperty(triGia);
        TinhTrang = new SimpleIntegerProperty(tinhTrang);
        AnhBia = new SimpleStringProperty(anhBia);
    }
}
