package com.javafx.librarian.controller;

import com.javafx.librarian.model.Sach;
import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.model.TheLoai;
import com.javafx.librarian.service.SachService;
import com.javafx.librarian.service.TacGiaService;
import com.javafx.librarian.service.TheLoaiService;
import com.javafx.librarian.utils.Util;
import javafx.beans.property.ObjectProperty;
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

public class EditSachController implements Initializable {
    private SachController sachController;
    private Sach sach;
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
        txtMaSach.setDisable(true);
        listTheLoai = FXCollections.observableArrayList(TheLoaiService.getInstance().getAllTheLoai());
        cbTheLoai.setTooltip(new Tooltip());
        cbTheLoai.setItems(listTheLoai);
        cbTheLoai.getSelectionModel().selectFirst();

        listTacGia = FXCollections.observableArrayList(TacGiaService.getInstance().getAllTacGia());
        cbTacGia.setTooltip(new Tooltip());
        cbTacGia.setItems(listTacGia);
        cbTacGia.getSelectionModel().selectFirst();

        new AutoCompleteComboBoxListener<>(cbTheLoai);
        new AutoCompleteComboBoxListener<>(cbTacGia);
    }

    public void setSachController(SachController sach) {
        this.sachController = sach;
    }

    public void setEditSach(Sach sach) {
        this.sach = sach;
        bindingData();
    }
    private void bindingData() {
        txtMaSach.setText(String.valueOf(sach.getMaSach()));
        txtTenSach.setText(sach.getTenSach());
        txtNXB.setText(sach.getNXB());
        txtNamXB.setText(String.valueOf(sach.getNamXB()));
        if (sach.getTinhTrang() == "Trống")
            rdbTrong.setSelected(true);
        else
            rdbDangMuon.setSelected(true);
        dtNgayNhap.setValue(Util.convertDateToLocalDateUI(sach.getNgayNhap()));
        txtTriGia.setText(String.valueOf(sach.getTriGia()));
        TheLoai theLoai = TheLoaiService.getInstance().getTheLoaiByID(sach.getMaTheLoai());
        TacGia tacGia = TacGiaService.getInstance().getTacGiaByID(sach.getMaTacGia());
        System.out.println(cbTheLoai.getItems());
        System.out.println(theLoai.toString());
        for(int i = 0; i < cbTheLoai.getItems().size(); i++)
        {
            if(cbTheLoai.getItems().get(i).toString().equals(theLoai.toString()))
            {
                cbTheLoai.getSelectionModel().select(i);
                break;
            }
        }
        for(int i = 0; i < cbTacGia.getItems().size(); i++)
        {
            if(cbTacGia.getItems().get(i).toString().equals(tacGia.toString()))
            {
                cbTacGia.getSelectionModel().select(i);
                break;
            }
        }
    }

    public void btnHuy_Click(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    public void btnLuu_Click(ActionEvent event) {
        //
        String maSach = txtMaSach.getText();
        String tenSach = txtTenSach.getText();
        String NXB = txtNXB.getText();
        int namXB = Integer.parseInt(txtNamXB.getText());
        Date ngayNhap = Date.valueOf(dtNgayNhap.getValue());
        int triGia = Integer.parseInt(txtTriGia.getText());
        String maTheLoai = (cbTheLoai.getSelectionModel().getSelectedItem().toString().split(" - "))[0].trim();
        String maTacGia = (cbTacGia.getSelectionModel().getSelectedItem().toString().split(" - "))[0].trim();
        int tinhTrang = rdbTrong.isSelected() ? 0 : 1;
        String anhBia = null;

        Sach sach = new Sach(maSach, tenSach, maTheLoai, maTacGia, namXB, NXB, ngayNhap, triGia, tinhTrang, anhBia);

        SachService.getInstance().editSach(sach);
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
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }
}
