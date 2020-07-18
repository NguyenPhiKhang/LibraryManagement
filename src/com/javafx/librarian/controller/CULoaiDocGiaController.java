package com.javafx.librarian.controller;

import com.javafx.librarian.model.LoaiDocGia;
import com.javafx.librarian.service.DocGiaService;
import com.javafx.librarian.service.LoaiDocGiaService;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class CULoaiDocGiaController {

    @FXML
    public TextField textMaLDG;
    @FXML
    public TextField textTenLDG;
    @FXML
    public JFXButton btnCapNhat;
    @FXML
    public JFXButton btnHuy;
    @FXML
    public JFXButton btnThem;
    @FXML
    public JFXButton btnClose;
    @FXML
    public FontAwesomeIcon iconClose;
    @FXML
    public Label labelLDG;
    @FXML
    public Pane panelCULDG;

    LoaiDocGia ldg;
    ObservableList<LoaiDocGia> listLDG;

    private double mousepX;
    private double mousepY;

    @FXML
    public void initialize(){
        panelCULDG.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelCULDG.setOnMouseDragged(mouseEvent -> {
            panelCULDG.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelCULDG.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });
    }

    public void btnCapNhatClicked(ActionEvent actionEvent) {
        this.ldg.setMaLoaiDocGia(textMaLDG.getText());
        this.ldg.setTenLoaiDocGia(textTenLDG.getText());

        int rs = LoaiDocGiaService.getInstance().updateLoaiDocGia(this.ldg);
        System.out.println(rs);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void setLabelLDG(String label){
        labelLDG.setText(label);
        if(Objects.equals(label, "Thêm loại độc giả")) {
            btnCapNhat.setVisible(false);
            textMaLDG.setDisable(false);
        }else{
            btnThem.setVisible(false);
            textMaLDG.setDisable(true);
        }
    }

    public void setListLDG(ObservableList<LoaiDocGia> listLDG){
        this.listLDG = listLDG;
    }

    public void setLoaiDocGia(LoaiDocGia ldg){
        this.ldg = ldg;

        textMaLDG.setText(String.valueOf(this.ldg.getMaLoaiDocGia()));
        textTenLDG.setText(this.ldg.getTenLoaiDocGia());
    }

    public void btnHuyClicked(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnThemClicked(ActionEvent actionEvent) {
        LoaiDocGia loaiDocGia = new LoaiDocGia();
        loaiDocGia.setMaLoaiDocGia(textMaLDG.getText());
        loaiDocGia.setTenLoaiDocGia(textTenLDG.getText());

        int rs = LoaiDocGiaService.getInstance().addLoaiDocGia(loaiDocGia);
        System.out.println(rs);
        LoaiDocGia loaidg = LoaiDocGiaService.getInstance().getLoaiDocGia(loaiDocGia.getMaLoaiDocGia());
        this.listLDG.add(loaidg);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnCloseAction(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnCloseMouseEnter(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: red; -fx-background-radius: 15");
        iconClose.setVisible(true);
    }

    public void btnCloseMouseExit(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #a6a6a6; -fx-background-radius: 15");
        iconClose.setVisible(false);
    }
}
