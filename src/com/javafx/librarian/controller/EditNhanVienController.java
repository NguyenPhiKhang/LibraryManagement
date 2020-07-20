package com.javafx.librarian.controller;

import com.javafx.librarian.model.Account;
import com.javafx.librarian.model.NhanVien;
import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.service.AccountService;
import com.javafx.librarian.service.NhanVienService;
import com.javafx.librarian.service.TacGiaService;
import com.javafx.librarian.utils.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;

import java.sql.Date;

public class EditNhanVienController {
    NhanVienController nhanVienController;
    NhanVien nv;
    //@FXML
    //public JFXButton btnDong;
    @FXML
    public TextField txtMaNV;
    @FXML
    public JFXButton btnCapNhat;
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
    @FXML
    public Button btnHuy;

    public void setNhanVienController(NhanVienController nv) {
        this.nhanVienController = nv;
    }

    public void setEditNV(NhanVien nv) {
        this.nv = nv;
        bindingData();
    }

    public void btnHuy_Click(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    private void bindingData() {
        Account acc = AccountService.getInstance().getUserById(nv.getIdAccount());
        txtMaNV.setText(String.valueOf(nv.getMaNV()));
        txtTenNV.setText(String.valueOf(nv.getTenNV()));
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(String.valueOf(nv.getEmail()));
        dpNgaySinh.setValue(Util.convertDateToLocalDateUI(nv.getNgaySinh()));
        txtSDT.setText(String.valueOf(nv.getSDT()));
        txtUsername.setText(acc.getUsername());
        txtPassword.setText(acc.getPassword());
    }

    public void btnCapNhat_Click(ActionEvent event) {
        // Update account
        Account acc = new Account(txtUsername.getText(), txtPassword.getText(), 1);
        AccountService.getInstance().editUser(acc);
        //
        NhanVien upNv = new NhanVien(
                nv.getMaNV(),
                txtTenNV.getText(),
                txtDiaChi.getText(),
                Date.valueOf(dpNgaySinh.getValue()),
                txtEmail.getText(),
                txtSDT.getText(),
                nv.getIdAccount()
        );
        //
        NhanVienService.getInstance().editNV(upNv);
        nhanVienController.refreshTable();
    }
}
