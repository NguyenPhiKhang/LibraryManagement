package com.javafx.librarian.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuSachController implements Initializable {
    @FXML
    public Tab tpSach;
    @FXML
    public Tab tpTheLoai;
    @FXML
    public Tab tpTacGia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/SachView.fxml"));
            AnchorPane docgiaView = (AnchorPane) loader.load();
            tpTacGia.setContent(docgiaView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
