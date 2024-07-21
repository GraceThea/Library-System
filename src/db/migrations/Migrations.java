package db.migrations;


import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bagon
 */
public abstract class Migrations {
    public String tableName;
    public String actionName;
    public String query;
    
    public String firstCreateStmt(){
        return "CREATE TABLE " + this.tableName + 
                "( id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,";
    }
    
    public String fieldString(String fieldName, Boolean notNull, String other){
        return " " + fieldName + " VARCHAR(255) " 
                + (notNull ? "NOT NULL " : " " ) + other + ",";
    }
    public String fieldInteger(String fieldName, Boolean notNull, String other){
        return " " + fieldName + " int " 
                + (notNull ? "NOT NULL " : " " ) + other + ",";
    }
    public String fieldDouble(String fieldName, Boolean notNull, String other){
        return " " + fieldName + " DOUBLE " 
                + (notNull ? "NOT NULL " : " " ) + other + ",";
    }
    public String fieldDate(String fieldName, Boolean notNull, String other){
        return " " + fieldName + " DATE " 
                + (notNull ? "NOT NULL " : " " ) + other + ",";
    }
    
    public String fieldText(String fieldName, Boolean notNull, String other){
        return " " + fieldName + " text " 
                + (notNull ? "NOT NULL " : " " ) + other + ",";
    }
    public String lastCreateStmt(){
        return " created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
               " updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE "
                + "CURRENT_TIMESTAMP)";
    }
    
    public abstract void doMigrate();
    
}
