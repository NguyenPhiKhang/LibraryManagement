package com.javafx.librarian.service;

import com.javafx.librarian.dao.TacGiaDAO;
import com.javafx.librarian.model.TacGia;

import java.util.List;

public class TacGiaService {
    private static TacGiaService instance;

    private TacGiaService() {
    }

    public static TacGiaService getInstance() {
        if (instance == null)
            instance = new TacGiaService();
        return instance;
    }

    public List<TacGia> getAllTacGia() {
        return TacGiaDAO.getInstance().getAllTacGia();
    }

    public TacGia getTacGiaByID(int ID) {
        return TacGiaDAO.getInstance().getTacGiaByID(ID);
    }

    public int addTacGia(int maTacGia, String tenTacGia) {
        return TacGiaDAO.getInstance().addTacGia(maTacGia, tenTacGia);
    }

    public int editTacGia(int maTacGia, String tenTacGia) {
        return TacGiaDAO.getInstance().editTacGia(maTacGia, tenTacGia);
    }

    public int deleteTacGia(int id) {
        return TacGiaDAO.getInstance().deleteTacGia(id);
    }

}
