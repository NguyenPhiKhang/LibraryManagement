package com.javafx.librarian.controller;

import com.javafx.librarian.model.DocGia;
import com.javafx.librarian.model.LoaiDocGia;
import com.javafx.librarian.service.DocGiaService;
import com.javafx.librarian.service.LoaiDocGiaService;
import com.javafx.librarian.utils.Util;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;
import java.util.stream.Collectors;

public class EditDocGiaController{
    @FXML
    public JFXButton btnClose;
    @FXML
    public FontAwesomeIcon iconClose;
    @FXML
    public Pane panelSuaDocGia;
    @FXML
    public TextField textMaDocGia;
    @FXML
    public JFXButton btnCapNhat;
    @FXML
    public JFXButton btnHuy;
    @FXML
    public JFXComboBox<String> cbbLoaiDG;
    @FXML
    public TextField textTenDocGia;
    @FXML
    public TextField textTinhTrangThe;
    @FXML
    public TextField textTongNo;
    @FXML
    public TextField textSoDienThoai;
    @FXML
    public TextField textDiaChi;
    @FXML
    public DatePicker dateNgayHetHan;
    @FXML
    public DatePicker dateNgaySinh;

    private double mousepX = 0;
    private double mousepY = 0;

    DocGia dg;
    ObservableList<LoaiDocGia> listLDG = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        panelSuaDocGia.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelSuaDocGia.setOnMouseDragged(mouseEvent -> {
            panelSuaDocGia.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelSuaDocGia.getScene().getWindow().setY(mouseEvent.getScreenY()- mousepY);
        });

        listLDG.addAll(LoaiDocGiaService.getInstance().getListLoaiDocGia());
    }

    public void setDocGia(DocGia dg){
        this.dg = dg;

        textMaDocGia.setText(String.valueOf(dg.getMaDocGia()));
        textTenDocGia.setText(dg.getTenDocGia());
        textTinhTrangThe.setText(String.valueOf(dg.getTinhTrangThe()));
        textTongNo.setText(String.valueOf(dg.getTongNo()));
        textDiaChi.setText(dg.getDiaChi());
        textSoDienThoai.setText(dg.getSoDienThoai());
        listLDG.forEach(ldg ->{
            cbbLoaiDG.getItems().add(ldg.getTenLoaiDocGia());
        });

        cbbLoaiDG.setValue(listLDG.stream().filter(ldg->ldg.getMaLoaiDocGia()==dg.getMaLoaiDocGia()).collect(Collectors.toList()).get(0).getTenLoaiDocGia());
        dateNgaySinh.setValue(Util.convertDateToLocalDateUI(dg.getNgaySinh()));
        dateNgayHetHan.setValue(Util.convertDateToLocalDateUI(dg.getNgayHetHan()));
    }

    public void btnCloseMouseEnter(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: red; -fx-background-radius: 15");
        iconClose.setVisible(true);
    }

    public void btnCloseMouseExit(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #a6a6a6; -fx-background-radius: 15");
        iconClose.setVisible(false);
    }

    public void btnCloseAction(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnCapNhatClicked(ActionEvent actionEvent) {
        this.dg.setTenDocGia(textTenDocGia.getText());
        this.dg.setTinhTrangThe(Byte.valueOf(textTinhTrangThe.getText()));
        this.dg.setTongNo(Double.parseDouble(textTongNo.getText()));
        this.dg.setDiaChi(textDiaChi.getText());
        this.dg.setSoDienThoai(textSoDienThoai.getText());
        this.dg.setNgaySinh(Date.valueOf(dateNgaySinh.getValue()));
        this.dg.setNgayHetHan(Date.valueOf(dateNgayHetHan.getValue()));
        this.dg.setMaLoaiDocGia(listLDG.stream().filter(ldg-> Objects.equals(ldg.getTenLoaiDocGia(), cbbLoaiDG.getValue())).collect(Collectors.toList()).get(0).getMaLoaiDocGia());

        int rs = DocGiaService.getInstance().updateDocGia(dg);
        System.out.println(rs);
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnHuyClicked(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }
}
