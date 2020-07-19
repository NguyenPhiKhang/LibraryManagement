package com.javafx.librarian.controller;

import com.javafx.librarian.model.Account;
import com.javafx.librarian.model.NhanVien;
import com.javafx.librarian.service.AccountService;
import com.javafx.librarian.service.NhanVienService;
import com.javafx.librarian.service.TacGiaService;
import com.javafx.librarian.utils.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddNhanVienController implements Initializable {
    NhanVienController nhanVienController;

    @FXML
    public Button btnDong;
    @FXML
    public Button btnThem;
    @FXML
    public TextField txtMaNV;
    @FXML
    public TextField txtTenNV;
    @FXML
    public DatePicker dpNgaySinh;
    @FXML
    public TextField txtDiaChi;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtSDT;
    @FXML
    public TextField txtUsername;
    @FXML
    public TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtMaNV.setText(Util.generateID(Util.PREFIX_CODE.NV));
    }

    public void setNhanVienController(NhanVienController nv) {
        this.nhanVienController = nv;
    }

    public void btnDong_Click(ActionEvent event) {
        Stage stage = (Stage) btnDong.getScene().getWindow();
        stage.close();
    }

    public void btnAddThem_Click(ActionEvent event) {
        String maNV = txtMaNV.getText();
        String tenNV = txtTenNV.getText();
        String diaChi = txtDiaChi.getText();
        String email = txtEmail.getText();
        String sdt = txtSDT.getText();
        Date ngaySinh = Date.valueOf(dpNgaySinh.getValue());
        String userName = txtUsername.getText();
        String passWord = txtPassword.getText();

        //create account according to new NhanVien
        Account acc = new Account(userName, passWord, 3);
        AccountService.getInstance().addUser(acc);

        //
        NhanVien nv = new NhanVien(maNV, tenNV, diaChi, ngaySinh, email, sdt, acc.getUsername());
        //
        int rest = NhanVienService.getInstance().addNV(nv);
        nhanVienController.refreshTable();
    }
}
