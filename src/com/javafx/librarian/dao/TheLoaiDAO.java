package com.javafx.librarian.dao;

import com.javafx.librarian.model.TheLoai;
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
            PreparedStatement ps = conn.prepareStatement("select * from tbtheloai where record_status is null");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int maTheLoai = res.getInt(1);
                String tenTheLoai = res.getString(2);
                ListTheLoai.add(new TheLoai(maTheLoai, tenTheLoai));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListTheLoai;
    }

    public TheLoai getTheLoaiByID(int ID) {
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbtheloai where matheloai=?");
            ps.setInt(1, ID);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                return new TheLoai(res.getInt(1), res.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int addTheloai(TheLoai theloai) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbtheloai values(?,?)");
            ps.setInt(1, theloai.getMaTheLoai());
            ps.setString(2, theloai.getTenTheLoai());

            res = ps.executeUpdate();
            System.out.println(res + "row is effected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public int editTheloai(TheLoai theloai) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection();) {
            PreparedStatement ps = conn.prepareStatement("update tbtheloai set tentheloai=? where matheloai=?");
            ps.setString(1, theloai.getTenTheLoai());
            ps.setInt(2, theloai.getMaTheLoai());
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int deleteTheLoai(int id) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection();) {
            PreparedStatement ps = conn.prepareStatement("update tbtheloai set record_status = 0 where matheloai=?");
            ps.setInt(1, id);
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}