/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.controller.custom.impl;

import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author midda
 */
public class IDController {
    public static String getLastID(String tblName, String colName) throws SQLException, ClassNotFoundException{
        String SQL=String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1",colName,tblName,colName);
        Connection conn=ConnectionFactory.getInstance().getConnection();
        Statement stm=conn.createStatement();
        ResultSet rst=stm.executeQuery(SQL);
        if(rst.next())
            return rst.getString(1);
        return null;
    }
}
