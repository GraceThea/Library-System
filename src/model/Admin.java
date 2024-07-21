/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.Database;
import db.migrations.CreateMigrationProduct;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bagon
 */
public class Admin extends Model {
    
    private String username;
    private String password;
    private String name;

    public Admin(ResultSet resultSet) throws SQLException {
        this.ID = resultSet.getInt("id");
        this.username = resultSet.getString("username");
        this.password = resultSet.getString("password");
        this.name = resultSet.getString("name");
        this.createdAt = resultSet.getTimestamp("created_at");
        this.updatedAt = resultSet.getTimestamp("updated_at");
        
        this.tableName = "admin";
    }

    public Admin(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        
        this.tableName = "admin";
    }
    

    @Override
    public Boolean insertIntoDatabae() {
        String query = "INSERT INTO " + this.tableName
                + " (username, password, name) VALUES "
                + "('" +this.username +"','" + this.password 
                + "','" + this.name + "'"; 
        
        try {
            int lastInsertedId = Database.stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.INFO, "{0} Success to insert", this.tableName);
            
            this.ID = lastInsertedId;
            Timestamp now = Timestamp.from(Instant.now());
            this.createdAt = now;
            this.updatedAt = now;
            
            return Boolean.TRUE;
        } catch (SQLException ex) {
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.SEVERE, "Failed to insert table", ex);
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean deleteFromDatabase() {
        String query = "DELETE FROM " + this.tableName
                + " WHERE id='"+this.ID+"'";
        
        try {
            Database.stm.executeUpdate(query);
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.INFO, "{0} Success to delete", this.tableName);
             return Boolean.TRUE;
        } catch (SQLException ex) {
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.SEVERE, "Failed to delete table", ex);
             return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateFromDatabase() {
        String query = "UPDATE " + this.tableName
                + " SET username='" + this.username +"', "
                + "password='" + this.password + "', "
                + "name='" + this.username + "' "
                + "WHERE id=" + String.valueOf(this.ID);
        
        try {
            Database.stm.executeUpdate(query);
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.INFO, "{0} Success to update", this.tableName);
             return Boolean.TRUE;
        } catch (SQLException ex) {
            Logger.getLogger(CreateMigrationProduct.class.getName())
                    .log(Level.SEVERE, "Failed to update table", ex);
             return Boolean.FALSE;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
