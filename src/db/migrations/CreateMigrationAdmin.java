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
public class CreateMigrationAdmin extends Migrations{

    public CreateMigrationAdmin() {
        this.tableName = "admin";
        this.actionName = "CREATE_TABLE_";
    }

    @Override
    public void doMigrate() {
         this.query = this.firstCreateStmt() 
                + this.fieldString("username", Boolean.TRUE, "")
                + this.fieldString("password", Boolean.TRUE, "")
                + this.fieldString("name", Boolean.TRUE, "")
                + this.lastCreateStmt();
        try {
            Database.stm.executeUpdate(query);
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.INFO, "{0} Success to create", this.tableName);
        } catch (SQLException ex) {
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.SEVERE, "Failed to create table", ex);
        }
    }
    
    
    
}
