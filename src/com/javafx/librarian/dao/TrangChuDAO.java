package com.javafx.librarian.dao;

import com.javafx.librarian.model.DocGia;
import com.javafx.librarian.model.Sach;
import com.javafx.librarian.model.TheLoai;
import com.javafx.librarian.service.SachService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TrangChuDAO {
    private static TrangChuDAO instance;

    private TrangChuDAO() {
    }

    public static TrangChuDAO getInstance() {
        if (instance == null) {
            instance = new TrangChuDAO();
        }
        return instance;
    }

    public Map<String, Integer> getAllFeature()
    {
        Map<String, Integer> rets = new HashMap<>();
        rets.put("sachs",SachDAO.getInstance().getCount());
        rets.put("nvs", NhanVienDAO.getInstance().getCount());
        rets.put("docgias", DocGiaDao.getInstance().getCount());
        rets.put("sachms", SachDAO.getInstance().getCountMuon());
         return rets;
    }

    public List<Sach> getNewSach() {
        List<Sach> ListSach = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from tbsach where record_status = 1 ORDER BY ngaynhap DESC LIMIT 10");
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
                Blob anhBiaBlob = res.getBlob(10);
                if (anhBiaBlob != null) {
                    InputStream anhBiaStream = anhBiaBlob.getBinaryStream();
                    File anhBia = File.createTempFile("temp", null);
                    org.apache.commons.io.FileUtils.copyInputStreamToFile(anhBiaStream, anhBia);
                    FileInputStream anhBiaDTO = new FileInputStream(anhBia);

                    ListSach.add(new Sach(maSach, tenSach, maTheLoai, maTacGia, namXb, nxb, ngayNhap, triGia, tinhTrang, anhBiaDTO));
                } else
                    ListSach.add(new Sach(maSach, tenSach, maTheLoai, maTacGia, namXb, nxb, ngayNhap, triGia, tinhTrang, null));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListSach;
    }

    public List<String> getActionMuonTra()
    {
        List<String> rets = new ArrayList<>();

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT d.tendocgia, s.tensach, pm.ngaymuon from tbdocgia d, tbphieumuon pm, tbctphieumuon ct, tbsach s WHERE ct.maphieumuon = pm.maphieumuon and pm.madocgia = d.madocgia AND ct.masach = s.masach order by pm.ngaymuon desc limit 10");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                rets.add("Đọc giả " + res.getString(1) + " vừa mượn sách " + res.getString(2) + " vào lúc " + res.getDate(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = JDBCConnection.getJDBCConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT d.tendocgia, s.tensach, pt.ngaytra from tbdocgia d, tbphieutra pt, tbctphieutra ct, tbsach s WHERE ct.maphieutra = pt.maphieutra and pt.madocgia = d.madocgia AND ct.masach = s.masach ORDER BY `pt`.`ngaytra` ASC limit 10");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                rets.add("Đọc giả " + res.getString(1) + " vừa trả sách " + res.getString(2) + " vào lúc " + res.getDate(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Comparator<String> compareAction = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String v1 =  o1.split("lúc")[1].trim();
                String v2 =  o2.split("lúc")[1].trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse(v1);
                LocalDate d2 = LocalDate.parse(v2);
                return d2.compareTo(d1);
            }
        };

        rets.sort(compareAction);

        return rets;
    }
}
