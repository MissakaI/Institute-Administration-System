/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.TutorDTO;

/**
 *
 * @author midda
 */
public interface TutorDAO extends SuperDAO<TutorDTO>{

    @Override
    public default ArrayList<TutorDTO> get(TutorDTO dto) throws Exception {return null;}
    
    @Override
    public default boolean update(TutorDTO dto) throws Exception {return false;}

    @Override
    public default boolean delete(TutorDTO dto) throws Exception {return false;}

    @Override
    public default TutorDTO search(TutorDTO dto) throws Exception {return null;}
    
}
