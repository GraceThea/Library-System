
import config.Config;
import db.Database;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.AppUtils;
import views.ErrorFrame;
import views.PublicViewApp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bagon
 */
public class App {
    public static void main(String[] args) {
        try {
            Config.readConfig();
            Database.openConnection();
            
            java.awt.EventQueue.invokeLater(() -> {
               PublicViewApp view = new PublicViewApp();
               AppUtils.showApp(view);
            });
        } catch (FileNotFoundException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            AppUtils.showApp(new ErrorFrame(ex.getMessage()));
            
        }
    }
}
