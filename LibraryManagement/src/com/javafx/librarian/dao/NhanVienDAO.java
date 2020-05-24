package com.javafx.librarian.dao;

import com.javafx.librarian.model.NhanVien;
import com.javafx.librarian.model.TacGia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhanVienDAO {
    private static NhanVienDAO instance;

    private NhanVienDAO() {
    }

    ;

    public static NhanVienDAO getInstance() {
        if (instance == null) {
            instance = new NhanVienDAO();
        }
        return instance;
    }

    public List<NhanVien> getAllNV() {
        List<NhanVien> ListNV = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbadmin");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int maNV = res.getInt(1);
                String tenNV = res.getString(3);
                Date ngaySinh = res.getDate(4);
                String diaChi = res.getString(5);
                String email = res.getString(6);
                String sdt = res.getString(7);
                ListNV.add(new NhanVien(maNV, tenNV, diaChi, ngaySinh, email, sdt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListNV;
    }
}
