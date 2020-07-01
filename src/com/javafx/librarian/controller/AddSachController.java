package com.javafx.librarian.controller;

import com.javafx.librarian.model.Sach;
import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.model.TheLoai;
import com.javafx.librarian.service.SachService;
import com.javafx.librarian.service.TacGiaService;
import com.javafx.librarian.service.TheLoaiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddSachController implements Initializable {
    private SachController sachController;
    @FXML
    public TextField txtMaSach;
    @FXML
    public TextField txtTenSach;
    @FXML
    public ComboBox cbTheLoai;
    @FXML
    public ComboBox cbTacGia;
    @FXML
    public TextField txtNXB;
    @FXML
    public TextField txtNamXB;
    @FXML
    public DatePicker dtNgayNhap;
    @FXML
    public TextField txtTriGia;
    @FXML
    public RadioButton rdbTrong;
    @FXML
    public RadioButton rdbDangMuon;
    @FXML
    public Button btnThem;
    @FXML
    public Button btnHuy;

    private ObservableList<TheLoai> listTheLoai;
    private ObservableList<TacGia> listTacGia;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listTheLoai = FXCollections.observableArrayList(TheLoaiService.getInstance().getAllTheLoai());
        cbTheLoai.setItems(listTheLoai);
        cbTheLoai.getSelectionModel().selectFirst();

        listTacGia = FXCollections.observableArrayList(TacGiaService.getInstance().getAllTacGia());
        cbTacGia.setItems(listTacGia);
        cbTacGia.getSelectionModel().selectFirst();
        dtNgayNhap.setValue(LocalDate.now());
    }

    public void setSachController(SachController sach) {
        this.sachController = sach;
    }

    public void btnHuy_Click(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    public void btnThem_Click(ActionEvent event) {
        //
        int maSach = Integer.parseInt(txtMaSach.getText());
        String tenSach = txtTenSach.getText();
        String NXB = txtNXB.getText();
        int namXB = Integer.parseInt(txtNamXB.getText());
        Date ngayNhap = Date.valueOf(dtNgayNhap.getValue());
        int triGia = Integer.parseInt(txtTriGia.getText());
        int maTheLoai = ((TheLoai) cbTheLoai.getSelectionModel().getSelectedItem()).getMaTheLoai();
        int maTacGia = ((TacGia) cbTacGia.getSelectionModel().getSelectedItem()).getMaTacGia();
        int tinhTrang = rdbTrong.isSelected() ? 0 : 1;
        String anhBia = null;

        Sach sach = new Sach(maSach, tenSach, maTheLoai, maTacGia, namXB, NXB, ngayNhap, triGia, tinhTrang, anhBia);

        SachService.getInstance().addSach(sach);
        sachController.refreshTable();
        txtTenSach.setText("");
        cbTheLoai.getSelectionModel().selectFirst();
        cbTacGia.getSelectionModel().selectFirst();
        txtNXB.setText("");
        txtNamXB.setText("");
        dtNgayNhap.setValue(LocalDate.now());
        rdbTrong.setSelected(false);
        rdbDangMuon.setSelected(false);
        txtTriGia.setText("");
    }
}
