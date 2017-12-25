/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.StudentDTO;

/**
 *
 * @author midda
 */
public interface StudentDAO extends SuperDAO<StudentDTO>{

    @Override
    public default boolean update(StudentDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(StudentDTO dto) throws Exception {return false;}

    @Override
    public default ArrayList<StudentDTO> get(StudentDTO dto) throws Exception {return null;}
}
