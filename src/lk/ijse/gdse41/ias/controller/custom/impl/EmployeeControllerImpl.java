/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import lk.ijse.gdse41.ias.controller.custom.EmployeeController;
import lk.ijse.gdse41.ias.dao.DAOFactory;
import lk.ijse.gdse41.ias.dao.custom.EmployeeDAO;
import lk.ijse.gdse41.ias.dto.EmployeeDTO;

/**
 *
 * @author midda
 */
public class EmployeeControllerImpl implements EmployeeController{

    EmployeeDAO employeeDAO;
    public EmployeeControllerImpl() {
        employeeDAO=(EmployeeDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    }


    @Override
    public boolean add(EmployeeDTO dto) throws Exception {
        return employeeDAO.add(dto);
    }
}
