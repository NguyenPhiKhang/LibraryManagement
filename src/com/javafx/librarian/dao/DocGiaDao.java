package com.javafx.librarian.dao;

import com.javafx.librarian.model.Account;
import com.javafx.librarian.model.DocGia;
import com.javafx.librarian.utils.Util;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DocGiaDao {
    private static DocGiaDao instance;

    private DocGiaDao(){}

    public static DocGiaDao getInstance(){
        if(instance == null){
            instance = new DocGiaDao();
        }
        return instance;
    }

    public List<DocGia> getListDocGia(){
        List<DocGia> docGias = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "select * from tbdocgia where record_status = 1";

        try {
            assert connection != null;
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                DocGia docgia = new DocGia();
                docgia.setMaDocGia(rs.getString("madocgia"));
                docgia.setTenDocGia(rs.getString("tendocgia"));
                docgia.setMaLoaiDocGia(rs.getString("maloaidocgia"));
                docgia.setNgaySinh(rs.getDate("ngaysinh"));
                docgia.setDiaChi(rs.getString("diachi"));
                docgia.setEmail(rs.getString("email"));
                docgia.setNgayLapThe(rs.getDate("ngaylapthe"));
                docgia.setNgayHetHan(rs.getDate("ngayhethan"));
                docgia.setTinhTrangThe(rs.getByte("tinhtrangthe"));
                docgia.setTongNo(rs.getDouble("tongno"));
                docgia.setIdAccount((rs.getString("idaccount")));
                docgia.setSoDienThoai(rs.getString("sdt"));

                docGias.add(docgia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return docGias;
    }

    public List<DocGia> searchDocGia(String find){
        List<DocGia> docGias = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "select * from tbdocgia where tendocgia is null or tendocgia = '' or tendocgia LIKE ?";

        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%"+find+"%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                DocGia docgia = new DocGia();
                docgia.setMaDocGia(rs.getString("madocgia"));
                docgia.setTenDocGia(rs.getString("tendocgia"));
                docgia.setMaLoaiDocGia(rs.getString("maloaidocgia"));
                docgia.setNgaySinh(rs.getDate("ngaysinh"));
                docgia.setDiaChi(rs.getString("diachi"));
                docgia.setEmail(rs.getString("email"));
                docgia.setNgayLapThe(rs.getDate("ngaylapthe"));
                docgia.setNgayHetHan(rs.getDate("ngayhethan"));
                docgia.setTinhTrangThe(rs.getByte("tinhtrangthe"));
                docgia.setTongNo(rs.getDouble("tongno"));
                docgia.setIdAccount((rs.getString("idaccount")));
                docgia.setSoDienThoai(rs.getString("sdt"));

                docGias.add(docgia);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return docGias;
    }

    public int deleteDocGia(String madg){
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "DELETE FROM tbdocgia WHERE madocgia=?";

        int rs=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, madg);

            rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int updateDocGia(DocGia dg){
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "Update tbdocgia set tendocgia=?, maloaidocgia=?, ngaysinh=?, ngayhethan=?, " +
                "tinhtrangthe=?, tongno=?, sdt=?, diachi=? where madocgia=?";

        int rs =  0;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dg.getTenDocGia());
            preparedStatement.setString(2, dg.getMaLoaiDocGia());
            preparedStatement.setDate(3, Date.valueOf(Util.convertDateToLocalDate(dg.getNgaySinh())));
            preparedStatement.setDate(4, Date.valueOf(Util.convertDateToLocalDate(dg.getNgayHetHan())));
            preparedStatement.setInt(5, dg.getTinhTrangThe());
            preparedStatement.setDouble(6, dg.getTongNo());
            preparedStatement.setString(7, dg.getSoDienThoai());
            preparedStatement.setString(8, dg.getDiaChi());
            preparedStatement.setString(9, dg.getMaDocGia());

            rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public int addDocGia(DocGia dg){
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO tbdocgia(tendocgia, maloaidocgia, ngaysinh, diachi, email, ngaylapthe, ngayhethan, tinhtrangthe, tongno, idaccount, sdt)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        int rs = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dg.getTenDocGia());
            preparedStatement.setString(2, dg.getMaLoaiDocGia());
            preparedStatement.setDate(3, Date.valueOf(Util.convertDateToLocalDate(dg.getNgaySinh())));
            preparedStatement.setString(4, dg.getDiaChi());
            preparedStatement.setString(5, dg.getEmail());
            preparedStatement.setDate(6, Date.valueOf(Util.convertDateToLocalDate(dg.getNgayLapThe())));
            preparedStatement.setDate(7, Date.valueOf(Util.convertDateToLocalDate(dg.getNgayHetHan())));
            preparedStatement.setInt(8, dg.getTinhTrangThe());
            preparedStatement.setDouble(9, dg.getTongNo());
            preparedStatement.setString(10, dg.getIdAccount());
            preparedStatement.setString(11, dg.getSoDienThoai());

            rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public DocGia getDocGia(String idaccount, String madg){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tbdocgia WHERE (madocgia=? or idaccount=?) LIMIT 1";

        DocGia docgia = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, madg);
            preparedStatement.setString(2, idaccount);

            ResultSet rsDG = preparedStatement.executeQuery();
            rsDG.last();

            docgia = new DocGia();
            docgia.setMaDocGia(rsDG.getString("madocgia"));
            docgia.setTenDocGia(rsDG.getString("tendocgia"));
            docgia.setMaLoaiDocGia(rsDG.getString("maloaidocgia"));
            docgia.setNgaySinh(rsDG.getDate("ngaysinh"));
            docgia.setDiaChi(rsDG.getString("diachi"));
            docgia.setEmail(rsDG.getString("email"));
            docgia.setNgayLapThe(rsDG.getDate("ngaylapthe"));
            docgia.setNgayHetHan(rsDG.getDate("ngayhethan"));
            docgia.setTinhTrangThe(rsDG.getByte("tinhtrangthe"));
            docgia.setTongNo(rsDG.getDouble("tongno"));
            docgia.setIdAccount((rsDG.getString("idaccount")));
            docgia.setSoDienThoai(rsDG.getString("sdt"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return docgia;
    }

    public DocGia getDocGiaByID(String madg){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "SELECT * FROM tbdocgia WHERE madocgia=? and record_status = 1";
        DocGia docgia = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, madg);

            ResultSet rsDG = preparedStatement.executeQuery();
            rsDG.last();

            docgia = new DocGia();
            docgia.setMaDocGia(rsDG.getString("madocgia"));
            docgia.setTenDocGia(rsDG.getString("tendocgia"));
            docgia.setMaLoaiDocGia(rsDG.getString("maloaidocgia"));
            docgia.setNgaySinh(rsDG.getDate("ngaysinh"));
            docgia.setDiaChi(rsDG.getString("diachi"));
            docgia.setEmail(rsDG.getString("email"));
            docgia.setNgayLapThe(rsDG.getDate("ngaylapthe"));
            docgia.setNgayHetHan(rsDG.getDate("ngayhethan"));
            docgia.setTinhTrangThe(rsDG.getByte("tinhtrangthe"));
            docgia.setTongNo(rsDG.getDouble("tongno"));
            docgia.setIdAccount((rsDG.getString("idaccount")));
            docgia.setSoDienThoai(rsDG.getString("sdt"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return docgia;
    }

    public List<DocGia> getListDocGiaToCB(){
        List<DocGia> docGias = new ArrayList<>();

        Connection connection = JDBCConnection.getJDBCConnection();

        String sql = "select * from tbdocgia where tinhtrangthe = '1' and record_status = '1' and madocgia NOT IN (SELECT madocgia FROM tbphieumuon WHERE tinhtrang = '1')";

        try {
            assert connection != null;
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                DocGia docgia = new DocGia();
                docgia.setMaDocGia(rs.getString("madocgia"));
                docgia.setTenDocGia(rs.getString("tendocgia"));
                docgia.setMaLoaiDocGia(rs.getString("maloaidocgia"));
                docgia.setNgaySinh(rs.getDate("ngaysinh"));
                docgia.setDiaChi(rs.getString("diachi"));
                docgia.setEmail(rs.getString("email"));
                docgia.setNgayLapThe(rs.getDate("ngaylapthe"));
                docgia.setNgayHetHan(rs.getDate("ngayhethan"));
                docgia.setTinhTrangThe(rs.getByte("tinhtrangthe"));
                docgia.setTongNo(rs.getDouble("tongno"));
                docgia.setIdAccount((rs.getString("idaccount")));
                docgia.setSoDienThoai(rs.getString("sdt"));

                docGias.add(docgia);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return docGias;
    }
}