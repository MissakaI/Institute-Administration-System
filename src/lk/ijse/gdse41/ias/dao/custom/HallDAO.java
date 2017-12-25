/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.dao.custom;

import lk.ijse.gdse41.ias.dao.SuperDAO;
import lk.ijse.gdse41.ias.dto.HallDTO;

/**
 *
 * @author midda
 */
public interface HallDAO extends SuperDAO<HallDTO>{
    
    @Override
    public default boolean delete(HallDTO dto) throws Exception {return false;}

    @Override
    public default HallDTO search(HallDTO dto) throws Exception {return null;}
    
}
