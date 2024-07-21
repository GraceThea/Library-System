/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.Database;
import db.migrations.CreateMigrationProduct;
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

public class Product extends Model {
    private String name;
    private String location;
    private Double price;
    private int quantity;

    public Product(String name, String location, Double price, int quantity) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.quantity = quantity;
        
        this.tableName = "product";
    }

    public Product(ResultSet rs) throws SQLException {
        
        this.ID = rs.getInt("id");
        this.createdAt = rs.getTimestamp("created_at");
        this.updatedAt = rs.getTimestamp("updated_at");
        this.name = rs.getString("name");
        this.location = rs.getString("location");
        this.price = rs.getDouble("price");
        this.quantity = rs.getInt("quantity");
        
        this.tableName = "product";
    }
    
   
    @Override
    public Boolean insertIntoDatabae() {
        String query = "INSERT INTO " + this.tableName
                + " (name, location, price, quantity) VALUES "
                + "('" +this.name +"', '"+this.location+"',"
                + String.valueOf(this.price) + ", " 
                + String.valueOf(this.quantity) + ")";
        
        try {
            Database.stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = Database.stm.getGeneratedKeys();
            rs.next();
            int lastInsertedId = rs.getInt(1);
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
                + " SET name='" + this.name +"', "
                + "location='" + this.location + "', "
                + "price=" + String.valueOf(this.price) + ", "
                + "quantity=" + String.valueOf(this.quantity) + " "
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {

        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
