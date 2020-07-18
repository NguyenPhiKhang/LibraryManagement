package com.javafx.librarian.controller;

import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.model.TheLoai;
import com.javafx.librarian.service.TacGiaService;
import com.javafx.librarian.service.TheLoaiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class EditTheLoaiController implements Initializable{
    private TheLoaiController theLoaiController;
    private TheLoai theloai;
    @FXML
    public TextField txtMaTheLoai;
    @FXML
    public TextField txtTenTheLoai;
    @FXML
    public Button btnLuu;
    @FXML
    public Button btnHuy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtMaTheLoai.setDisable(true);
    }

    public void setTheLoaiController(TheLoaiController theLoai) {
        this.theLoaiController = theLoai;
    }

    public void setEditTheLoai(TheLoai theLoai) {
        this.theloai = theLoai;
        bindingData();
    }

    public void btnHuy_Click(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    public void btnLuu_Click(ActionEvent event) {
        //
        String maTheLoai = txtMaTheLoai.getText();
        String tenTheLoai = txtTenTheLoai.getText();
        TheLoai theloai = new TheLoai(maTheLoai, tenTheLoai);
        //
        TheLoaiService.getInstance().editTheLoai(theloai);
        theLoaiController.refreshTable();
        txtMaTheLoai.setText("");
        txtTenTheLoai.setText("");
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    private void bindingData() {
        txtMaTheLoai.setText(String.valueOf(theloai.getMaTheLoai()));
        txtTenTheLoai.setText(theloai.getTenTheLoai());
    }
}
