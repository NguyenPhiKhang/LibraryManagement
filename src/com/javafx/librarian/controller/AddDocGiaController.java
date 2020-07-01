package com.javafx.librarian.controller;

import com.javafx.librarian.model.Account;
import com.javafx.librarian.model.DocGia;
import com.javafx.librarian.model.LoaiDocGia;
import com.javafx.librarian.service.AccountService;
import com.javafx.librarian.service.DocGiaService;
import com.javafx.librarian.service.LoaiDocGiaService;
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

import javax.print.Doc;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;

public class AddDocGiaController {
    @FXML
    public Pane panelThemDocGia;
    @FXML
    public TextField textEmail;
    @FXML
    public TextField textTenDocGia;
    @FXML
    public JFXComboBox<String> cbbLoaiDG;
    @FXML
    public DatePicker dateNgaySinh;
    @FXML
    public TextField textDiaChi;
    @FXML
    public TextField textSoDienThoai;
    @FXML
    public JFXComboBox<String> cbbAccount;
    @FXML
    public JFXButton btnThemAccount;
    @FXML
    public JFXButton btnThem;
    @FXML
    public JFXButton btnHuy;
    @FXML
    public JFXButton btnClose;
    @FXML
    public FontAwesomeIcon iconClose;

    private double mousepX = 0;
    private double mousepY = 0;

    ObservableList<Account> listAccount = FXCollections.observableArrayList();
    ObservableList<LoaiDocGia> listLDG = FXCollections.observableArrayList();
    ObservableList<DocGia> listDocGia;

    @FXML
    public void initialize(){
        panelThemDocGia.setOnMousePressed(mouseEvent -> {
            mousepX = mouseEvent.getSceneX();
            mousepY = mouseEvent.getSceneY();
        });

        panelThemDocGia.setOnMouseDragged(mouseEvent -> {
            panelThemDocGia.getScene().getWindow().setX(mouseEvent.getScreenX() - mousepX);
            panelThemDocGia.getScene().getWindow().setY(mouseEvent.getScreenY() - mousepY);
        });

        listLDG.addAll(LoaiDocGiaService.getInstance().getListLoaiDocGia());
        listLDG.forEach(ldg ->{
            cbbLoaiDG.getItems().add(ldg.getTenLoaiDocGia());
        });

        listAccount.addAll(AccountService.getInstance().getAllUsers());
        listAccount.forEach(acc->{
            cbbAccount.getItems().add(acc.getUsername());
        });
    }

    public void setListDG(ObservableList<DocGia> listDG){
        this.listDocGia = listDG;
    }

    public void btnThemClicked(ActionEvent actionEvent) {
        DocGia dg = new DocGia();
        dg.setTenDocGia(textTenDocGia.getText());
        dg.setMaLoaiDocGia(listLDG.stream().filter(ldg-> Objects.equals(ldg.getTenLoaiDocGia(), cbbLoaiDG.getValue())).collect(Collectors.toList()).get(0).getMaLoaiDocGia());
        dg.setEmail(textEmail.getText());
        dg.setNgaySinh(Date.valueOf(dateNgaySinh.getValue()));
        dg.setNgayLapThe(Date.valueOf(LocalDate.now()));
        dg.setNgayHetHan(Date.valueOf((LocalDate.now()).plusMonths(6)));
        dg.setIdAccount(cbbAccount.getValue());
        dg.setDiaChi(textDiaChi.getText());
        dg.setSoDienThoai(textSoDienThoai.getText());

        int rs = DocGiaService.getInstance().addDocGia(dg);
        System.out.println(rs);
        listDocGia.add(DocGiaService.getInstance().getDocGia(dg.getIdAccount(), dg.getMaDocGia()));
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnHuyClicked(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnCloseAction(ActionEvent actionEvent) {
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void btnCloseMouseEnter(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: red; -fx-background-radius: 15");
        iconClose.setVisible(true);
    }

    public void btnThemAccountClicked(ActionEvent actionEvent) {

    }

    public void btnCloseMouseExit(MouseEvent mouseEvent) {
        btnClose.setStyle("-fx-background-color: #a6a6a6; -fx-background-radius: 15");
        iconClose.setVisible(false);
    }
}
