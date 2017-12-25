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
import lk.ijse.gdse41.ias.controller.custom.SubjectController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dao.custom.SubjectDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.SubjectDTO;

/**
 *
 * @author midda
 */
public class SubjectControllerImpl implements SubjectController{

    SubjectDAO subjectDAO;
    public SubjectControllerImpl() {
        subjectDAO=(SubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUBJECT);
    }


    @Override
    public boolean add(SubjectDTO dto) throws Exception {
        return subjectDAO.add(dto);
    }

    @Override
    public ArrayList<SubjectDTO> get(SubjectDTO dto) throws Exception {
        QueryDAO queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        return queryDAO.getSubjectsOfBatch(dto.getBid());
    }

    @Override
    public ArrayList<SubjectDTO> getAll() throws Exception {
        return subjectDAO.getAll();
    }
    
}
