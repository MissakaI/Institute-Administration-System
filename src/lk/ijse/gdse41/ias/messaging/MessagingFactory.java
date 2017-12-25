/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.messaging;

import hu.ozeki.OzSMSMessage;
import hu.ozeki.OzSmsClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.ijse.gdse41.ias.dao.db.ConnectionFactory;

/**
 *
 * @author midda
 */
public class MessagingFactory extends OzSmsClient{
    
    private static MessagingFactory messagingFactory;
    private static Properties smsProperties;
    

    public MessagingFactory() throws IOException, InterruptedException {
        super("localhost",9500);
        //super(smsProperties.getProperty("ip"), Integer.parseInt(smsProperties.getProperty("port")));
    }
    
    public static MessagingFactory getInstance(){
        File dbFile=new File("settings/smsSettings.properties");
        smsProperties = new Properties();
        FileReader smsReader;
        
        try {
            smsReader=new FileReader(dbFile);
            smsProperties.load(smsReader);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(messagingFactory==null){
            try {
                messagingFactory=new MessagingFactory();
            } catch (IOException ex) {
                Logger.getLogger(MessagingFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(MessagingFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return messagingFactory;
    }
    
    public boolean login(){
        try {
            //login(smsProperties.getProperty("username"),smsProperties.getProperty("password"));
            super.login("admin", "admin");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MessagingFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MessagingFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isLoggedIn();
    }
    
//    public boolean logout(){
//        try {
//            super.logout();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(MessagingFactory.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return !(isLoggedIn());
//    }
    

    @Override
    public void doOnMessageReceived(OzSMSMessage osmsm) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnMessageDeliveredToHandset(OzSMSMessage osmsm) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnMessageDeliveredToNetwork(OzSMSMessage osmsm) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnMessageAcceptedForDelivery(OzSMSMessage osmsm) {
        System.out.println("delievered");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnMessageDeliveryError(OzSMSMessage osmsm) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnClientConnectionError(int i, String string) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
