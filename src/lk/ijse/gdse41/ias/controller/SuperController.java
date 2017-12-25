/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller;

import java.util.ArrayList;
import lk.ijse.gdse41.ias.dto.SuperDTO;

/**
 *
 * @author midda
 */
public interface SuperController<T extends SuperDTO> {
    public boolean add(T dto) throws Exception;
    
    public boolean update(T dto) throws Exception;
    
    public boolean delete(T dto) throws Exception;
    
    public T search(T dto) throws Exception;
    
    public ArrayList<T> get(T dto) throws Exception;
    
    public ArrayList<T> getAll() throws Exception;
}
