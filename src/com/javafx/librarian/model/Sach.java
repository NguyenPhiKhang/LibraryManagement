package com.javafx.librarian.model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class Sach {
    private StringProperty MaSach;
    private StringProperty TenSach;
    private StringProperty MaTheLoai;
    private StringProperty MaTacGia;
    private IntegerProperty NamXB;
    private StringProperty NXB;
    private ObjectProperty<Date> NgayNhap;
    private IntegerProperty TriGia;
    private StringProperty TinhTrang;
    private StringProperty AnhBia;

    public String getMaSach() {
        return MaSach.get();
    }

    public StringProperty maSachProperty() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        this.MaSach.set(maSach);
    }

    public String getTenSach() { return TenSach.get();}

    public StringProperty tenSachProperty() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        this.TenSach.set(tenSach);
    }

    public String getMaTheLoai() {
        return MaTheLoai.get();
    }

    public StringProperty maTheLoaiProperty() {
        return MaTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.MaTheLoai.set(maTheLoai);
    }

    public String getMaTacGia() {
        return MaTacGia.get();
    }

    public StringProperty maTacGiaProperty() {
        return MaTacGia;
    }

    public void setMaTacGia(String maTacGia) {
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

    public Date getNgayNhap() { return NgayNhap.get();}

    public ObjectProperty<Date> ngayNhapProperty() { return NgayNhap;}

    public void setNgayNhap(Date ngayNhap) { this.NgayNhap.set(ngayNhap);}

    public int getTriGia() {
        return TriGia.get();
    }

    public IntegerProperty triGiaProperty() {
        return TriGia;
    }

    public void setTriGia(int triGia) {
        this.TriGia.set(triGia);
    }

    public String getTinhTrang() {
        return TinhTrang.get();
    }

    public StringProperty tinhTrangProperty() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.TinhTrang.set(tinhTrang);
    }

    public String getAnhBia() { return AnhBia.get();}

    public StringProperty anhBiaProperty() {
        return AnhBia;
    }

    public void setAnhBia(String anhBia) {
        this.AnhBia.set(anhBia);
    }

    public Sach(String maSach, String tenSach, String maTheLoai, String maTacGia, int namXB, String nxb, Date ngayNhap, int triGia, int tinhTrang, String anhBia) {
        MaSach = new SimpleStringProperty(maSach);
        TenSach = new SimpleStringProperty(tenSach);
        MaTheLoai = new SimpleStringProperty(maTheLoai);
        MaTacGia = new SimpleStringProperty(maTacGia);
        NamXB = new SimpleIntegerProperty(namXB);
        NXB = new SimpleStringProperty(nxb);
        NgayNhap = new SimpleObjectProperty<Date>(ngayNhap);
        TriGia = new SimpleIntegerProperty(triGia);
        if(tinhTrang == 0)
        {
            TinhTrang = new SimpleStringProperty("Trống");
        }
        else if(tinhTrang == 1)
        {
            TinhTrang = new SimpleStringProperty("Đang mượn");
        }
        else if(tinhTrang == 2)
        {
            TinhTrang = new SimpleStringProperty("Hư hỏng");
        }
        else {
            TinhTrang = new SimpleStringProperty("Mất");
        }
        AnhBia = new SimpleStringProperty(anhBia);
    }
}
