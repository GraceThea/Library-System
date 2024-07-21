/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import config.Constants;
import db.Database;
import java.awt.Frame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Product;
import views.ErrorDialog;
import views.SuccessDialog;

/**
 *
 * @author bagon
 */
public class AppUtils {
    public static void showDialogError(Frame parent, boolean modal, String errMsg, String title){
        ErrorDialog error = new ErrorDialog(parent, modal, errMsg);
        error.setLocationRelativeTo(null);
        error.setTitle(title);
        error.setVisible(true);
    }
    public static void showDialogSuccess(Frame parent, boolean modal, String errMsg, String title){
        SuccessDialog success = new SuccessDialog(parent, modal, errMsg);
        success.setLocationRelativeTo(null);
        success.setTitle(title);
        success.setVisible(true);
    }
    
    public static void redirctApp(JFrame from, JFrame to){
        from.dispose();
        from.setVisible(false);
        
        to.setLocationRelativeTo(null);
        to.setVisible(true);
    }
    public static void showApp(JFrame to){
        to.setLocationRelativeTo(null);
        to.setVisible(true);
    }
    
    public static void setMark(JLabel mark, JLabel map, JPanel holder, int x, int y, JFrame parent){
        mark.setVisible(true);
        holder.remove(map);
        holder.add(mark, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, -1, 110));
        holder.add(map, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        
        parent.repaint();
        parent.validate();
    }
    
    /**
     *
     * @param table
     * @param rows
     */
    public static void fetchDataToTable(JTable table, List<Vector<Object>> rows){
         String[] columnNames = {"ID", "Product Name", "Quantity", "Price","Brand"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0);
        for(var i = 0; i < model.getRowCount(); i++){
            model.removeRow(i);
        }
        rows.forEach(row -> {
            model.addRow(row);
        });
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
    }
    
    public static void fetchDataProduct(String condition, JTable table) throws SQLException{
        List<Vector<Object>> rows = new ArrayList<>();
        ResultSet rs = Database.get(Constants.TABLE_PRODUCT, condition);
        while(rs.next()){
            Product product = new Product(rs);
            Vector<Object> row = new Vector<>(5);
            row.add(product.getID());
            row.add(product.getName());
            row.add(product.getQuantity());
            row.add(product.getPrice());
            row.add(product.getLocation().split("#")[2]);

            rows.add(row);
        }
        AppUtils.fetchDataToTable(table, rows);
    }
}
