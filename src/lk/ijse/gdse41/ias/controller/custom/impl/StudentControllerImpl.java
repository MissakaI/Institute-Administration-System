  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.RegistrationController;
import lk.ijse.gdse41.ias.controller.custom.StudentController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dao.custom.StudentDAO;

/**
 *
 * @author midda
 */
public class StudentControllerImpl implements StudentController{

    Connection conn;
    StudentDAO studentDAO;
    QueryDAO queryDAO;
    
    public StudentControllerImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
        studentDAO=(StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
        queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }
    
    

    @Override
    public boolean add(StudentDTO studentDTO, ArrayList<RegistrationDTO> dtoList) throws Exception {
        conn.setAutoCommit(false);
        try{
            if(add(studentDTO)){
                RegistrationController ctrlRegistration=(RegistrationController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.REGISTRATION);
                if(ctrlRegistration.add(dtoList)){
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
    public boolean add(StudentDTO s) throws Exception {
        return studentDAO.add(s);
    }

    @Override
    public boolean update(StudentDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(StudentDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StudentDTO search(StudentDTO dto) throws Exception {
        return studentDAO.search(dto);
    }

    @Override
    public ArrayList<StudentDTO> get(StudentDTO dto) throws Exception {
        return queryDAO.getStudentsInClass(dto.getCid());
    }

    @Override
    public ArrayList<StudentDTO> getAll() throws Exception {
        return studentDAO.getAll();
    }
    
    
    
}
