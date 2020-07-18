package com.javafx.librarian.dao;

import com.javafx.librarian.model.CTPhieuMuon;
import com.javafx.librarian.model.PhieuMuon;
import com.javafx.librarian.model.Sach;
import com.javafx.librarian.utils.Util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private static PhieuMuonDAO instance;

    private PhieuMuonDAO() {
    }

    public static PhieuMuonDAO getInstance() {
        if (instance == null) {
            instance = new PhieuMuonDAO();
        }
        return instance;
    }

    public List<PhieuMuon> getAllPhieuMuon() {
        List<PhieuMuon> ListPM = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbphieumuon where record_status = 1");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String maPM = res.getString(1);
                String maDG = res.getString(2);
                Date ngayMuon = res.getDate(3);
                Date hanTra = res.getDate(4);
                int tinhTrang = res.getInt(5);
                ListPM.add(new PhieuMuon(maPM, maDG, ngayMuon, hanTra, tinhTrang));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListPM;
    }

    public List<CTPhieuMuon> getAllCTPhieuMuonByMaPM(String maPM) {
        List<CTPhieuMuon> ListCTPM = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select ct.masach,s.tensach,tl.tentheloai,tg.tentacgia from tbsach s, tbctphieumuon ct, tbtheloai tl,tbtacgia tg where ct.masach = s.masach and s.matheloai=tl.matheloai and s.matacgia=tg.matacgia and s.record_status = 1 and tl.record_status = 1 and tg.record_status = 1 and ct.maphieumuon = ?");
            ps.setString(1, maPM);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String mapm = maPM;
                String maSach = res.getString(1);
                String tenSach = res.getString(2);
                String theLoai = res.getString(3);
                String tacGia = res.getString(4);
                ListCTPM.add(new CTPhieuMuon(maPM, maSach, tenSach, theLoai, tacGia));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListCTPM;
    }

    public int addPhieuMuon(PhieuMuon phieuMuon) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbphieumuon(`maphieumuon`, `madocgia`, `ngaymuon`, `hantra`, `tinhtrang`) values(?,?,?,?,?)");
            ps.setString(1, phieuMuon.getMaPM());
            ps.setString(2, phieuMuon.getMaDG());
            ps.setDate(3,Date.valueOf(Util.convertDateToLocalDate(phieuMuon.getNgayMuon())));
            ps.setDate(4,Date.valueOf(Util.convertDateToLocalDate(phieuMuon.getHanTra())));
            ps.setInt(5,phieuMuon.getTinhTrang() == "Trả đủ" ? 0 : 1);
            res = ps.executeUpdate();
            System.out.println(res + "row is effected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    public int addCTPhieuMuon(CTPhieuMuon ctphieuMuon) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbctphieumuon(`maphieumuon`, `masach`) values(?,?)");
            ps.setString(1, ctphieuMuon.getMaPM());
            ps.setString(2, ctphieuMuon.getMaSach());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps1 = conn.prepareStatement("update tbsach set tinhtrang=1 where masach=?");
            ps1.setString(1, ctphieuMuon.getMaSach());
            res = ps1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int giaHanPhieuMuon(String maPM, java.util.Date hanTra) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("update tbphieumuon set hantra=? where maphieumuon=?");
            ps.setDate(1,Date.valueOf(Util.convertDateToLocalDate(hanTra)));
            ps.setString(2,maPM);
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}
