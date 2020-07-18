package com.javafx.librarian.controller;

import com.javafx.librarian.model.TheLoai;
import com.javafx.librarian.service.TheLoaiService;
import com.javafx.librarian.utils.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTheLoaiController implements Initializable{
    private TheLoaiController theLoaiController;
    @FXML
    public TextField txtMaTheLoai;
    @FXML
    public TextField txtTenTheLoai;
    @FXML
    public Button btnThem;
    @FXML
    public Button btnHuy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtMaTheLoai.setText(Util.generateID(Util.PREFIX_CODE.TL));
        txtMaTheLoai.setDisable(true);
    }

    public void setTheLoaiController(TheLoaiController theLoai) {
        this.theLoaiController = theLoai;
    }

    public void btnHuy_Click(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    public void btnThem_Click(ActionEvent event) {
        //
        String maTheLoai = txtMaTheLoai.getText();
        String tenTheLoai = txtTenTheLoai.getText();
        TheLoai theloai = new TheLoai(maTheLoai, tenTheLoai);
        //
        TheLoaiService.getInstance().addTheLoai(theloai);
        theLoaiController.refreshTable();
        txtMaTheLoai.setText(Util.generateID(Util.PREFIX_CODE.TL));
        txtTenTheLoai.setText("");
    }
}
