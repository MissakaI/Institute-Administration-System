/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom;

import lk.ijse.gdse41.ias.controller.SuperController;
import lk.ijse.gdse41.ias.dto.SubjectDTO;

/**
 *
 * @author midda
 */
public interface SubjectController extends SuperController<SubjectDTO>{

    @Override
    public default boolean update(SubjectDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default boolean delete(SubjectDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public default SubjectDTO search(SubjectDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
