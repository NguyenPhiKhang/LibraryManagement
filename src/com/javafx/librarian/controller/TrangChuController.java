package com.javafx.librarian.controller;

import com.javafx.librarian.model.Sach;
import com.javafx.librarian.service.TrangChuService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TrangChuController implements Initializable {
    @FXML
    private Label lbSach;
    @FXML
    private Label lbDocGia;
    @FXML
    private Label lbNhanVien;
    @FXML
    private Label lbSachMuon;
    @FXML
    private VBox boxSach;
    @FXML
    private VBox boxAction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Map<String, Integer> features = TrangChuService.getInstance().getAllFeature();
        lbSach.setText(features.get("sachs") + " SÁCH");
        lbDocGia.setText(features.get("docgias") + " ĐỌC GIẢ");
        lbNhanVien.setText(features.get("nvs") + " NHÂN VIÊN");
        lbSachMuon.setText(features.get("sachms") + " SÁCH ĐANG MƯỢN");

        List<Sach> new10Sach = TrangChuService.getInstance().getNewSach();
        new10Sach.forEach(e -> {
            Label row = new Label();
            row.setText(e.getMaSach() + " - " + e.getTenSach());
            boxSach.getChildren().add(row);
        });

        List<String> new10Action = TrangChuService.getInstance().getActionMuonTra();
        new10Action.forEach(e -> {
            Label row = new Label();
            row.setText(e);
            boxAction.getChildren().add(row);
        });

    }
}
