/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.custom.HallController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.HallDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.HallDTO;

/**
 *
 * @author midda
 */
public class HallControllerImpl implements HallController{

    HallDAO hallDAO;
    public HallControllerImpl() {
        hallDAO=(HallDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.HALL);
    }

    @Override
    public boolean add(HallDTO dto) throws Exception {
        return hallDAO.add(dto);
    }

    @Override
    public boolean update(HallDTO dto) throws Exception {
        return hallDAO.update(dto);
    }

    @Override
    public ArrayList<HallDTO> get(HallDTO dto) throws Exception {
        return hallDAO.get(dto);
    }

    @Override
    public ArrayList<HallDTO> getAll() throws Exception {
        return hallDAO.getAll();
    }
    
}
