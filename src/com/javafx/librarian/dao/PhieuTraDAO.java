package com.javafx.librarian.dao;

import com.javafx.librarian.model.CTPhieuTra;
import com.javafx.librarian.model.PhieuTra;
import com.javafx.librarian.utils.Util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhieuTraDAO {
    private static PhieuTraDAO instance;

    private PhieuTraDAO() {
    }

    public static PhieuTraDAO getInstance() {
        if (instance == null) {
            instance = new PhieuTraDAO();
        }
        return instance;
    }

    public List<PhieuTra> getAllPhieuTra() {
        List<PhieuTra> ListPT = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbphieutra where record_status = 1");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String maPT = res.getString(1);
                String maPM = res.getString(2);
                String maDG = res.getString(3);
                Date ngayTra = res.getDate(4);
                Double tienPhat = res.getDouble(5);
                ListPT.add(new PhieuTra(maPT ,maPM, maDG, ngayTra, tienPhat));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListPT;
    }

    public List<CTPhieuTra> getAllCTPhieuTraByMaPT(String maPT) {
        List<CTPhieuTra> ListCTPT = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select ct.masach, s.tensach, ct.songaymuon, ct.tienphat from tbsach s, tbctphieutra ct where ct.masach = s.masach and s.record_status = 1 and ct.maphieutra = ?");
            ps.setString(1, maPT);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                String maSach = res.getString(1);
                String tenSach = res.getString(2);
                int soNgayMuon = res.getInt(3);
                int tienPhat = res.getInt(4);
                ListCTPT.add(new CTPhieuTra(maPT, maSach, tenSach,null, soNgayMuon, tienPhat));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListCTPT;
    }

    public int addPhieuTra(PhieuTra phieuTra) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbphieutra(`maphieutra`, `maphieumuon`, `madocgia`, `ngaytra`, `tienphatkynay`) values(?,?,?,?,?)");
            ps.setString(1, phieuTra.getMaPT());
            ps.setString(2, phieuTra.getMaPM());
            ps.setString(3,phieuTra.getMaDG());
            ps.setDate(4,Date.valueOf(Util.convertDateToLocalDateUI(phieuTra.getNgayTra()).plusDays(1)));
            ps.setDouble(5,phieuTra.getTienPhatKyNay());
            res = ps.executeUpdate();
            System.out.println(res + "row is effected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        double tongno = DocGiaDao.getInstance().getDocGiaByID(phieuTra.getMaDG()).getTongNo() + phieuTra.getTienPhatKyNay();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps1 = conn.prepareStatement("update tbdocgia set tongno=? where madocgia=?");
            ps1.setDouble(1, tongno);
            ps1.setString(2, phieuTra.getMaDG());
            res = ps1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int addCTPhieuTra(CTPhieuTra ctPhieuTra) {
        int res = 0;
        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("insert into tbctphieutra(`maphieutra`, `masach`, `songaymuon`, `tienphat`) values(?,?,?,?)");
            ps.setString(1, ctPhieuTra.getMaPT());
            ps.setString(2, ctPhieuTra.getMaSach());
            ps.setInt(3, ctPhieuTra.getSoNM());
            ps.setDouble(4, ctPhieuTra.getTienPhat());
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps1 = conn.prepareStatement("update tbsach set tinhtrang=0 where masach=?");
            ps1.setString(1, ctPhieuTra.getMaSach());
            res = ps1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
