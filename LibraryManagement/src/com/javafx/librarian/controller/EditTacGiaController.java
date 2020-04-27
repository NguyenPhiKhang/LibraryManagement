package com.javafx.librarian.controller;

import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.service.TacGiaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTacGiaController implements Initializable {
    private TacGiaController tacGiaController;
    private TacGia tacGia;
    @FXML
    TextField txtEditMaTacGia;
    @FXML
    TextField txtEditTenTacGia;
    @FXML
    Button btnEditThem;
    @FXML
    Button btnEditDong;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setTacGia(TacGiaController tacGia) {
        this.tacGiaController = tacGia;
    }

    public void setEditTacGia(TacGia tacGia) {
        this.tacGia = tacGia;
    }

    public void btnAddDong_Click(ActionEvent event) {
        Stage stage = (Stage) btnEditDong.getScene().getWindow();
        stage.close();
    }

    public void btnAddThem_Click(ActionEvent event) {
        //
        int maTacGia = Integer.parseInt(txtEditMaTacGia.getText());
        String tenTacGia = txtEditTenTacGia.getText();
        //
        TacGiaService.getInstance().addTacGia(maTacGia, tenTacGia);
        tacGiaController.refreshTable();
    }
}
