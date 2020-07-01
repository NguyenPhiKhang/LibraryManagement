package com.javafx.librarian.controller;

import com.javafx.librarian.model.Account;
import com.javafx.librarian.model.NhanVien;
import com.javafx.librarian.service.AccountService;
import com.javafx.librarian.service.NhanVienService;
import com.javafx.librarian.service.TacGiaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class AddNhanVienController {
    NhanVienController nhanVienController;

    @FXML
    public Button btnDong;
    @FXML
    public Button btnThem;
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

    public void setNhanVienController(NhanVienController nv) {
        this.nhanVienController = nv;
    }

    public void btnDong_Click(ActionEvent event) {
        Stage stage = (Stage) btnDong.getScene().getWindow();
        stage.close();
    }

    public void btnAddThem_Click(ActionEvent event) {
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

        NhanVien nv = new NhanVien(tenNV, diaChi, ngaySinh, email, sdt, acc.getUsername());
        //
        int rest = NhanVienService.getInstance().addNV(nv);
        nhanVienController.refreshTable();
    }
}
