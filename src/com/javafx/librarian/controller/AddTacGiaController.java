package com.javafx.librarian.controller;

import com.javafx.librarian.service.TacGiaService;
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

public class AddTacGiaController implements Initializable {
    private TacGiaController TacGia;
    @FXML
    public TextField txtAddMaTacGia;
    @FXML
    public TextField txtAddTenTacGia;
    @FXML
    public Button btnAddThem;
    @FXML
    public Button btnAddDong;

    public TacGiaController tacGiaController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtAddMaTacGia.setText(Util.generateID(Util.PREFIX_CODE.TG));
        txtAddMaTacGia.setDisable(true);
    }

    public void setTacGia(TacGiaController tacGia) {
        this.tacGiaController = tacGia;
    }

    public void btnAddDong_Click(ActionEvent event) {
        Stage stage = (Stage) btnAddDong.getScene().getWindow();
        stage.close();
    }

    public void btnAddThem_Click(ActionEvent event) {
        //
        String maTacGia = txtAddMaTacGia.getText();
        String tenTacGia = txtAddTenTacGia.getText();
        //
        TacGiaService.getInstance().addTacGia(maTacGia, tenTacGia);
        tacGiaController.refreshTable();
    }
}
