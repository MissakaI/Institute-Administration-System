/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.BatchDTO;

/**
 *
 * @author midda
 */
public interface BatchController extends SuperController<BatchDTO>{

    @Override
    public default boolean update(BatchDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default boolean delete(BatchDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default ArrayList<BatchDTO> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean add(ArrayList<BatchDTO> dtoList) throws Exception;
    
    public ArrayList<String> getSections() throws Exception;
    
    public ArrayList<Integer> getSectionYears(String section) throws Exception;
    
}
