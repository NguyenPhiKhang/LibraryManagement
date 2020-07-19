package com.javafx.librarian.model;

import javafx.beans.property.*;

import java.util.Date;

public class CTPhieuTra {
    private StringProperty MaPT;
    private StringProperty MaSach;
    public StringProperty TenSach;
    public ObjectProperty<Date> NgayMuon;
    public IntegerProperty SoNM;
    public DoubleProperty TienPhat;

    public CTPhieuTra(String maPT, String maSach, String tenSach, Date ngayMuon, int soNM, double tienPhat) {
        MaPT = new SimpleStringProperty(maPT);
        MaSach = new SimpleStringProperty(maSach);
        TenSach = new SimpleStringProperty(tenSach);
        NgayMuon = new SimpleObjectProperty<Date>(ngayMuon);
        SoNM = new SimpleIntegerProperty(soNM);
        TienPhat = new SimpleDoubleProperty(tienPhat);
    }

    public String getMaPT() {
        return MaPT.get();
    }

    public StringProperty maPTProperty() {
        return MaPT;
    }

    public void setMaPT(String maPT) {
        this.MaPT.set(maPT);
    }

    public String getMaSach() {
        return MaSach.get();
    }

    public StringProperty maSachProperty() {
        return MaSach;
    }

    public void setMaSach(String maSach) {
        this.MaSach.set(maSach);
    }

    public String getTenSach() {
        return TenSach.get();
    }

    public StringProperty tenSachProperty() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        this.TenSach.set(tenSach);
    }

    public Date getNgayMuon() {
        return NgayMuon.get();
    }

    public ObjectProperty<Date> ngayMuonProperty() {
        return NgayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.NgayMuon.set(ngayMuon);
    }

    public int getSoNM() {
        return SoNM.get();
    }

    public IntegerProperty soNMProperty() {
        return SoNM;
    }

    public void setSoNM(int soNM) {
        this.SoNM.set(soNM);
    }

    public double getTienPhat() {
        return TienPhat.get();
    }

    public DoubleProperty tienPhatProperty() {
        return TienPhat;
    }

    public void setTienPhat(double tienPhat) {
        this.TienPhat.set(tienPhat);
    }
}
