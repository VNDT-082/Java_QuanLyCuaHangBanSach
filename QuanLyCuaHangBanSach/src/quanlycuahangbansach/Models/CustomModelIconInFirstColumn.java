/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangbansach.Models;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class CustomModelIconInFirstColumn extends AbstractTableModel{
    private String[]    column;
    private Object[][]  rows;
    public CustomModelIconInFirstColumn(){}
    public CustomModelIconInFirstColumn(Object[][] data, String[] columnName)
    {
        this.column=columnName;
        this.rows=data;
    }
    @Override
    public Class getColumnClass(int column)
    {
        if(column==0)
        {
            return Icon.class;
        }
        else
        {
            return getValueAt(0, column).getClass();
        }
    
    }

    @Override
    public int getRowCount() {
        return this.rows.length;
    }

    @Override
    public int getColumnCount() {
        return this.column.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.rows[rowIndex][columnIndex];
    }
    @Override
    public String getColumnName(int column)
    {
        return this.column[column];
    }
    
}
