package com.javafx.librarian.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuSachController implements Initializable {
    @FXML
    public BorderPane borderPaneTheLoai;
    @FXML
    public BorderPane borderPaneSach;
    @FXML
    public BorderPane borderPaneTacGia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //.setCenter(getPage("../view/docgia/DocGiaView.fxml"));
        borderPaneTheLoai.setCenter(getPage("../view/TheLoaiView.fxml"));
        borderPaneSach.setCenter(getPage("../view/SachView.fxml"));
        borderPaneTacGia.setCenter(getPage("../view/TacGiaView.fxml"));
    }

    private <T> T getPage(String url) {
        T menuSachView = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(url));
            menuSachView = (T) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuSachView;
    }
}
