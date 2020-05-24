package com.javafx.librarian.controller;

import com.javafx.librarian.model.NhanVien;
import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.service.NhanVienService;
import com.javafx.librarian.service.TacGiaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class NhanVienController implements Initializable {

    @FXML
    public TableView<NhanVien> tbhienthi;
    @FXML
    public TableColumn<NhanVien, Integer> tbcMaNV;
    @FXML
    public TableColumn<NhanVien, String> tbcTenNV;
    @FXML
    public TableColumn<NhanVien, String> tbcDiaChi;
    @FXML
    public TableColumn<NhanVien, Date> tbcNgaySinh;
    @FXML
    public TableColumn<NhanVien, String> tbcEmail;
    @FXML
    public TableColumn<NhanVien, String> tbcSDT;
    @FXML
    public TextField txtMaNV;
    @FXML
    public TextField txtTenNV;
    @FXML
    public TextField txtDiaChi;
    @FXML
    public TextField txtNgaySinh;
    @FXML
    public TextField txtEmail;
    @FXML
    public TextField txtSDT;
    @FXML
    public Button btnThem;
    @FXML
    public Button btnXoa;
    @FXML
    public Button btnSua;

    //region controller
    private ObservableList<NhanVien> listNV;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCell();
        loadData();
    }

    private void setCell() {
        tbcMaNV.setCellValueFactory(cellData -> cellData.getValue().maNVProperty().asObject());
        tbcTenNV.setCellValueFactory(cellData -> cellData.getValue().tenNVProperty());
        tbcDiaChi.setCellValueFactory(cellData -> cellData.getValue().diaChiProperty());
        tbcNgaySinh.setCellValueFactory(cellData -> cellData.getValue().ngaySinhProperty());
        tbcEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tbcSDT.setCellValueFactory(cellData -> cellData.getValue().SDTProperty());
    }

    private void loadData() {
        listNV = FXCollections.observableArrayList(NhanVienService.getInstance().getAllNV());
        tbhienthi.setItems(listNV);
    }
}
