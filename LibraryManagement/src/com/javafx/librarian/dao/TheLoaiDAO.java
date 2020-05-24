package com.javafx.librarian.dao;

import com.javafx.librarian.model.TheLoai;
import com.javafx.librarian.model.User;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private static TheLoaiDAO instance;

    private TheLoaiDAO() {
    }

    public static TheLoaiDAO getInstance() {
        if (instance == null) {
            instance = new TheLoaiDAO();
        }
        return instance;
    }

    public List<TheLoai> getAllTheLoai() {
        List<TheLoai> ListTheLoai = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbtheloai");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int kq1 = res.getInt(1);
                String kq2 = res.getString(2);
                ListTheLoai.add(new TheLoai(res.getInt(1), res.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListTheLoai;
    }

    public int addTacGia(int maTacGia, String tenTacGia) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbtacgia values(?,?)");
            ps.setInt(1, maTacGia);
            ps.setString(2, tenTacGia);

            res = ps.executeUpdate();
            System.out.println(res + "row is effected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public int editTacGia(int maTacGia, String tenTacGia) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection();) {
            PreparedStatement ps = conn.prepareStatement("update tbtacgia set tentacgia=? where matacgia=?");
            ps.setString(1, tenTacGia);
            ps.setInt(2, maTacGia);
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteTacGia(int id) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection();) {
            PreparedStatement ps = conn.prepareStatement("delete from tbtacgia where matacgia=?");
            ps.setInt(1, id);
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
