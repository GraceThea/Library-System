/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.ResultSet;

/**
 *
 * @author bagon
 */
public abstract class Model {
    public int ID;
    public Timestamp createdAt;
    public Timestamp updatedAt;
    public String tableName;
    
    public abstract Boolean insertIntoDatabae();
    public abstract Boolean deleteFromDatabase();
    public abstract Boolean updateFromDatabase();
}
