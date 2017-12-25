/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.EmployeeDTO;

/**
 *
 * @author midda
 */
public interface EmployeeController extends SuperController<EmployeeDTO>{

    @Override
    public default boolean update(EmployeeDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default boolean delete(EmployeeDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default EmployeeDTO search(EmployeeDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default ArrayList<EmployeeDTO> get(EmployeeDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default ArrayList<EmployeeDTO> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
