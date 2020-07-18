package com.javafx.librarian.dao;

import com.javafx.librarian.model.Sach;
import com.javafx.librarian.model.TheLoai;
import javafx.collections.ObservableList;
import com.javafx.librarian.utils.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private static SachDAO instance;

    private SachDAO() {
    }

    public static SachDAO getInstance() {
        if (instance == null) {
            instance = new SachDAO();
        }
        return instance;
    }

    public List<Sach> getAllSach() {
        List<Sach> ListSach = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbsach where record_status = 1");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String maSach = res.getString(1);
                String tenSach = res.getString(2);
                String maTheLoai = res.getString(3);
                String maTacGia = res.getString(4);
                int namXb = res.getInt(5);
                String nxb = res.getString(6);
                Date ngayNhap = res.getDate(7);
                int triGia = res.getInt(8);
                int tinhTrang = res.getInt(9);
                String anhBia = res.getString(10);
                ListSach.add(new Sach(maSach, tenSach, maTheLoai, maTacGia, namXb, nxb, ngayNhap, triGia, tinhTrang, anhBia));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListSach;
    }

    public int addSach(Sach sach) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbsach(`masach`, `tensach`, `matheloai`, `matacgia`, `namxb`, `nxb`, `ngaynhap`, `trigia`, `tinhtrang`, `anhbia`) values(?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, sach.getMaSach());
            ps.setString(2, sach.getTenSach());
            ps.setString(3,sach.getMaTheLoai());
            ps.setString(4, sach.getMaTacGia());
            ps.setInt(5, sach.getNamXB());
            ps.setString(6,sach.getNXB());
            ps.setDate(7,Date.valueOf(Util.convertDateToLocalDate(sach.getNgayNhap())));
            ps.setInt(8,sach.getTriGia());
            ps.setInt(9,sach.getTinhTrang() == "Trống" ? 0 : 1);
            ps.setString(10,sach.getAnhBia());

            res = ps.executeUpdate();
            System.out.println(res + "row is effected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public int editSach(Sach sach) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection();) {
            PreparedStatement ps = conn.prepareStatement("update tbsach set tensach=?, matheloai=?, matacgia=?, namxb=?, nxb=?, ngaynhap=?, trigia=?, tinhtrang=?, anhbia=? where masach=?");
            ps.setString(1, sach.getTenSach());
            ps.setString(2,sach.getMaTheLoai());
            ps.setString(3, sach.getMaTacGia());
            ps.setInt(4, sach.getNamXB());
            ps.setString(5,sach.getNXB());
            ps.setDate(6,Date.valueOf(Util.convertDateToLocalDate(sach.getNgayNhap())));
            ps.setInt(7,sach.getTriGia());
            ps.setInt(8,sach.getTinhTrang()== "Trống" ? 0 : 1);
            ps.setString(9,sach.getAnhBia());
            ps.setString(10, sach.getMaSach());
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteSach(String id) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection();) {
            PreparedStatement ps = conn.prepareStatement("update tbsach set record_status=0 where masach=?");
            ps.setString(1, id);
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Sach> getAllSachByDocGiaInPM(String maDocGia) {
        List<Sach> ListSach = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbsach where record_status = 1 and masach in (select masach from tbctphieumuon ct, tbphieumuon pm WHERE ct.maphieumuon = pm.maphieumuon and pm.madocgia = ? and pm.tinhtrang = 1 and ct.record_status = 1) ");
            ps.setString(1, maDocGia);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String maSach = res.getString(1);
                String tenSach = res.getString(2);
                String maTheLoai = res.getString(3);
                String maTacGia = res.getString(4);
                int namXb = res.getInt(5);
                String nxb = res.getString(6);
                Date ngayNhap = res.getDate(7);
                int triGia = res.getInt(8);
                int tinhTrang = res.getInt(9);
                String anhBia = res.getString(10);
                ListSach.add(new Sach(maSach, tenSach, maTheLoai, maTacGia, namXb, nxb, ngayNhap, triGia, tinhTrang, anhBia));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListSach;
    }

    public Sach getSachByID(String maSach) {
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbsach where record_status = 1 and masach = ?");
            ps.setString(1, maSach);
            ResultSet res = ps.executeQuery();
            res.next();
                String masach = res.getString(1);
                String tenSach = res.getString(2);
                String maTheLoai = res.getString(3);
                String maTacGia = res.getString(4);
                int namXb = res.getInt(5);
                String nxb = res.getString(6);
                Date ngayNhap = res.getDate(7);
                int triGia = res.getInt(8);
                int tinhTrang = res.getInt(9);
                String anhBia = res.getString(10);
                return new Sach(masach, tenSach, maTheLoai, maTacGia, namXb, nxb, ngayNhap, triGia, tinhTrang, anhBia);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
