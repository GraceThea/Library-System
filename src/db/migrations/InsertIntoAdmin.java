/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.migrations;

import db.Database;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bagon
 */
public class InsertIntoAdmin extends Migrations {

    public InsertIntoAdmin() {
        this.actionName = "INSERT_TO_";
        this.tableName = "admin";
    }
    
    
    @Override
    public void doMigrate() {
        this.query = "INSERT INTO " + this.tableName
                + " (username, password, name) VALUES "
                + "('Admin', 'admin', 'First Admin')";
        
        try {
            Database.stm.executeUpdate(query);
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.INFO, "{0} Success to insert", this.tableName);
        } catch (SQLException ex) {
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.SEVERE, "Failed to insert table", ex);
        }
    }
    
}
