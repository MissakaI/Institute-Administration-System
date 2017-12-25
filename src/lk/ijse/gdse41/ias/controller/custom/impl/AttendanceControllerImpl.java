/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.custom.AttendanceController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.AttendanceDAO;
import lk.ijse.gdse41.ias.dao.custom.QueryDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.AttendanceDTO;

/**
 *
 * @author midda
 */
public class AttendanceControllerImpl implements AttendanceController{

    AttendanceDAO attendanceDAO;
    QueryDAO queryDAO;
            
    public AttendanceControllerImpl() {
        attendanceDAO=(AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
        queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }
    
    @Override
    public boolean add(AttendanceDTO dto) throws Exception {
        return attendanceDAO.add(dto);
    }

    @Override
    public ArrayList<AttendanceDTO> get(AttendanceDTO dto) throws Exception {
        return queryDAO.getAttendanceToClass(dto.getId(), dto.getA_date());
    }

}
