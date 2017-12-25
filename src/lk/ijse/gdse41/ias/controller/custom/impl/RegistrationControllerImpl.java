package lk.ijse.gdse41.ias.controller.custom.impl;

import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.RegistrationDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author midda
 */
public class RegistrationControllerImpl implements lk.ijse.gdse41.ias.controller.custom.RegistrationController{

    RegistrationDAO registrationDAO;
    public RegistrationControllerImpl() {
        registrationDAO=(RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    }
    
    @Override
    public boolean add(ArrayList<RegistrationDTO> dtoList) throws Exception {
        for (RegistrationDTO registrationDTO : dtoList) {
            if(!add(registrationDTO)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean add(RegistrationDTO dto) throws Exception {
        return registrationDAO.add(dto);
    }

    @Override
    public RegistrationDTO search(RegistrationDTO dto) throws Exception {
        return registrationDAO.search(dto);
    }
}
