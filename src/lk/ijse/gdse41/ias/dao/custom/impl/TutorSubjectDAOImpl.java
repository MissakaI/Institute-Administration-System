/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import lk.ijse.gdse41.ias.dao.custom.TutorSubjectDAO;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;

/**
 *
 * @author midda
 */
public class TutorSubjectDAOImpl implements TutorSubjectDAO{
    
    Connection conn;
    public TutorSubjectDAOImpl() {
        conn=ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public boolean add(TutorSubjectDTO dto) throws Exception {
        String SQL="INSERT INTO tutor_subject VALUES (?,?,?)";
        PreparedStatement stm=conn.prepareStatement(SQL);
        stm.setObject(1, dto.getTsid());
        stm.setObject(2, dto.getTid());
        stm.setObject(3, dto.getSubId());
        return stm.executeUpdate()>0;
        
    }
    
    
    
}
