package com.javafx.librarian.controller;

import com.javafx.librarian.model.TacGia;
import com.javafx.librarian.service.TacGiaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SachController implements Initializable {
    //region khai báo biến controls
    @FXML
    public TableView<TacGia> tbhienthi;
    @FXML
    public TableColumn<TacGia, Integer> tbcMaTacGia;
    @FXML
    public TableColumn<TacGia, String> tbcTenTacGia;
    @FXML
    public TextField txtTenTacGia;
    @FXML
    public TextField txtMaTacGia;
    @FXML
    public Button btnThem;
    @FXML
    public Button btnXoa;
    @FXML
    public Button btnSua;
    @FXML
    public Button btnLuu;
    //endregion

    //region controller
    private ObservableList<TacGia> listTacGia;
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCell();
        loadData();
        txtMaTacGia.setDisable(true);
        txtTenTacGia.setDisable(true);
    }

    private void setCell() {
        tbcMaTacGia.setCellValueFactory(cellData -> cellData.getValue().maTacGiaProperty().asObject());
        tbcTenTacGia.setCellValueFactory(cellData -> cellData.getValue().tenTacGiaProperty());
    }

    private void loadData() {
        TacGiaService.getInstance().getAllTacGia();
        listTacGia = FXCollections.observableArrayList(TacGiaService.getInstance().getAllTacGia());
        tbhienthi.setItems(listTacGia);
    }

    private void refreshTable() {
        listTacGia.clear();
        loadData();
    }

    private void clearInput() {
        txtTenTacGia.setText("");
        txtMaTacGia.setText("");
    }

    public void bindingData() {
        TacGia temp = tbhienthi.getSelectionModel().getSelectedItem();
        txtMaTacGia.setText((String.valueOf(temp.getMaTacGia())));
        txtTenTacGia.setText(temp.getTenTacGia());
    }

    public void btnXoa_Click(ActionEvent event) {
        TacGia temp = tbhienthi.getSelectionModel().getSelectedItem();
        TacGiaService.getInstance().deleteTacGia(temp.getMaTacGia());
        refreshTable();
        clearInput();
    }

    public void btnThem_Click(ActionEvent event) {
        clearInput();
        txtMaTacGia.setDisable(false);
        txtTenTacGia.setDisable(false);
    }

    public void btnSua_Click(ActionEvent event) {
        String tenTacGiaMoi = txtTenTacGia.getText();
        TacGia temp = tbhienthi.getSelectionModel().getSelectedItem();
        TacGiaService.getInstance().editTacGia(temp.getMaTacGia(), tenTacGiaMoi);
        refreshTable();
    }

    public void btnLuu_Click(ActionEvent event) {
        int maTacGia = Integer.parseInt(txtMaTacGia.getText());
        String tenTacGia = txtTenTacGia.getText();
        TacGiaService.getInstance().addTacGia(maTacGia, tenTacGia);
        refreshTable();
    }

}
