/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.AttendanceDTO;

/**
 *
 * @author midda
 */
public interface AttendanceDAO extends SuperDAO<AttendanceDTO>{

    @Override
    public default boolean update(AttendanceDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(AttendanceDTO dto) throws Exception {return false;}

    @Override
    public default AttendanceDTO search(AttendanceDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<AttendanceDTO> getAll() throws Exception {return null;}
    
}
