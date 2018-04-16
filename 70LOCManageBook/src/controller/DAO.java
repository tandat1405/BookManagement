/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Display.Books;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author My Fucking PC
 */


public class DAO {
    private Connection conn;
    int n;
    public DAO(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=Book;username=sa;password=123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean addBook(Books b){
        String sql = "INSERT INTO tblBook(code, name, author, publisher, year) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, b.getCode());
            ps.setString(2, b.getName());
            ps.setString(3, b.getAuthor());
            ps.setString(4, b.getPublisher());
            ps.setInt(5, b.getYear());
            
            return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void deleteBook(String code){
        String sql = "DELETE from tblBook where code = '"+code+"' ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public ArrayList<Books> getlistBook(){
        ArrayList<Books> list = new ArrayList<>();
        String sql = "SELECT * FROM tblBook";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            Books b = new Books();
            b.setCode(rs.getString("code"));
            b.setName(rs.getString("name"));
            b.setAuthor(rs.getString("author"));
            b.setPublisher(rs.getString("publisher"));
            b.setYear(rs.getInt("year"));
            
            list.add(b);
        }
        } catch (Exception e) {
        }
        return list;
    }
    
}
