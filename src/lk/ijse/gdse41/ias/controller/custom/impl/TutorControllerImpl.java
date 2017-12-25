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
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dao.custom.impl.TutorDAOImpl;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;

/**
 *
 * @author midda
 */
public class TutorControllerImpl implements TutorController{

    Connection conn;
    TutorDAOImpl tutorDAOImpl;
    public TutorControllerImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
        tutorDAOImpl=(TutorDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TUTOR);
    }
    
    

    @Override
    public boolean add(TutorDTO tutor, ArrayList<TutorSubjectDTO> dtoList) throws Exception {
        conn.setAutoCommit(false);
        try{
            if(add(tutor)){
                TutorSubjectControllerImpl impl=(TutorSubjectControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR_SUBJECTS);
                if(impl.add(dtoList)){
                    conn.commit();
                    
                    return true;
                }
            }
            conn.rollback();
            return false;
        }finally{
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean add(TutorDTO dto) throws Exception {
        return tutorDAOImpl.add(dto);
    }

    @Override
    public ArrayList<TutorDTO> get(TutorDTO dto) throws Exception {
        QueryDAO queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
        return queryDAO.getTutorsTeachingSubjectForBatch(dto);
    }

    @Override
    public ArrayList<TutorDTO> getAll() throws Exception {
        return tutorDAOImpl.getAll();
    }
    
}
