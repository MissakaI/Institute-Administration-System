/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;

/**
 *
 * @author midda
 */
public interface ClassScheduleDAO extends SuperDAO<ClassScheduleDTO>{
    
    public ArrayList<ClassScheduleDTO> getHallSchedule(ClassScheduleDTO dto) throws Exception;

    @Override
    public default boolean delete(ClassScheduleDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default ClassScheduleDTO search(ClassScheduleDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default ArrayList<ClassScheduleDTO> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
