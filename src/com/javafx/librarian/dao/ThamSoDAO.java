package com.javafx.librarian.dao;

import com.javafx.librarian.model.Sach;
import com.javafx.librarian.model.ThamSo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThamSoDAO {
    private static ThamSoDAO instance;

    private ThamSoDAO() {
    }

    public static ThamSoDAO getInstance() {
        if (instance == null) {
            instance = new ThamSoDAO();
        }
        return instance;
    }

    public ThamSo getThamSo() {
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbthamso where record_status = 1");
            ResultSet res = ps.executeQuery();
            res.next();
            int maxTuoi = res.getInt(1);
            int minTuoi = res.getInt(2);
            int hanThe = res.getInt(3);
            int soTacGia = res.getInt(4);
            int KhoangCachXB = res.getInt(5);
            int MaxSachMuon = res.getInt(6);
            int HanMuon = res.getInt(7);
            int TienPhat = res.getInt(8);

            return new ThamSo(maxTuoi, minTuoi, hanThe, soTacGia, KhoangCachXB, MaxSachMuon, HanMuon, TienPhat);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
