/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import db.Database;
import db.migrations.CreateMigrationAdmin;
import db.migrations.CreateMigrationProduct;
import db.migrations.InsertIntoAdmin;
import db.migrations.InsertIntoProduct;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bagon
 */
public class MigrationControl {
    public String tableNameMigrate = "migration_list";
    public List<String> listAlreadyMigrate = new ArrayList<>();
    
    public void checkTableMigrations() throws SQLException{
        DatabaseMetaData dbm = Database.connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableNameMigrate, null);
        if(tables.next()){
            String query = "SELECT * FROM " + this.tableNameMigrate;
            ResultSet migration =  Database.stm.executeQuery(query);
            while(migration.next()){
                this.listAlreadyMigrate.add( migration.getString("migrationName") + migration.getString("tableName"));
            }
        }else{
            String query = "CREATE TABLE " + this.tableNameMigrate + " " + 
                    "( id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," + 
                    " tableName VARCHAR(255) not Null," +
                    " migrationName VARCHAR(255) not Null," + 
                    " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    " updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)";
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
            Date date = new Date();  
            
            Database.stm.executeUpdate(query);
            System.out.println("Success to create " 
                    + this.tableNameMigrate + " at " + formatter.format(date));
        }
        
    };
    
    public void doMigration(){
        try {
            this.checkTableMigrations();
            
            CreateMigrationProduct product = new CreateMigrationProduct();
            if(!this.listAlreadyMigrate.contains( product.actionName + product.tableName)){
                product.doMigrate();
                this.addItToMigrate(product.tableName, product.actionName);
            }
            CreateMigrationAdmin admin = new CreateMigrationAdmin();
            if(!this.listAlreadyMigrate.contains( admin.actionName + admin.tableName)){
                admin.doMigrate();
                this.addItToMigrate(admin.tableName, admin.actionName);
            }
            InsertIntoAdmin inAdmin = new InsertIntoAdmin();
            if(!this.listAlreadyMigrate.contains( inAdmin.actionName + inAdmin.tableName)){
                inAdmin.doMigrate();
                this.addItToMigrate(inAdmin.tableName, inAdmin.actionName);
            }
            InsertIntoProduct inProduct = new InsertIntoProduct();
            if(!this.listAlreadyMigrate.contains( inProduct.actionName + inProduct.tableName)){
                inProduct.doMigrate();
                this.addItToMigrate(inProduct.tableName, inProduct.actionName);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MigrationControl.class.getName())
                    .log(Level.SEVERE, "Internal server error :", ex);
        }
        
    }
    
    public void addItToMigrate(String tableName, String migrationName) throws SQLException{
        String query = "INSERT INTO " + this.tableNameMigrate + " (tableName, migrationName) "
                + "VALUES('" + tableName + "','" + migrationName + "')";

        Database.stm.executeUpdate(query);
        Logger.getLogger(MigrationControl.class.getName())
                    .log(Level.INFO, "{0}{1} MIGRATED", new Object[]{migrationName, tableName});
    }
}
