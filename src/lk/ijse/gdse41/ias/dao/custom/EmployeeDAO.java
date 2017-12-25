/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.EmployeeDTO;

/**
 *
 * @author midda
 */
public interface EmployeeDAO extends SuperDAO<EmployeeDTO>{

    @Override
    public default boolean update(EmployeeDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(EmployeeDTO dto) throws Exception {return false;}

    @Override
    public default EmployeeDTO search(EmployeeDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<EmployeeDTO> get(EmployeeDTO dto) throws Exception {return null;}

    @Override
    public default ArrayList<EmployeeDTO> getAll() throws Exception {return null;}
    
}
