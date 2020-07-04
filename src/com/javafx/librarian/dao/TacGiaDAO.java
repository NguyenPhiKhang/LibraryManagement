package com.javafx.librarian.dao;

import com.javafx.librarian.model.TacGia;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TacGiaDAO {
    private static TacGiaDAO instance;

    private TacGiaDAO() {
    }

    public static TacGiaDAO getInstance() {
        if (instance == null) {
            instance = new TacGiaDAO();
        }
        return instance;
    }

    public List<TacGia> getAllTacGia() {
        List<TacGia> ListTacGia = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbtacgia where record_status = 1");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int kq1 = res.getInt(1);
                String kq2 = res.getString(2);
                ListTacGia.add(new TacGia(res.getInt(1), res.getString(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListTacGia;
    }

    public TacGia getTacGiaByID(int ID) {
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbtacgia where matacgia=?");
            ps.setInt(1, ID);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return new TacGia(res.getInt(1), res.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addTacGia(int maTacGia, String tenTacGia) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbtacgia(matacgia, tentacgia) values(?,?)");
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
            PreparedStatement ps = conn.prepareStatement("update tbtacgia set record_status = 0 where matacgia=?");
            ps.setInt(1, id);
            res = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
