/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macana
 */
public class Funcion {
    
public void listarProductos(JTable tablaProductos)
    {
     DefaultTableModel model;
    String [] titulos={"codigo producto", "nombre producto","cantidad"};
    String [] registros = new String [3];
           model= new DefaultTableModel(null,titulos);
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT pr.codigo_producto, pr.nombre_producto,pl.cantidad_x_lote FROM producto as pr, lote_producto as pl WHERE pr.codigo_producto= pl.codigo_producto");
                while(rs.next()){
                    registros[0]= rs.getString("pr.codigo_producto");
                    registros[1]=rs.getString("pr.nombre_producto");
                     registros[2]=rs.getString("pl.cantidad_x_lote ");
                     model.addRow(registros);
                                }
                tablaProductos.setModel(model);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
               catch( java.lang.NumberFormatException e)
               {
                   System.out.println("error producido en void listarProductos "+e.toString());
               }
    }
    
 public void listarCliente(JTable tablaClientes)
    {
     DefaultTableModel model;
    String [] titulos={"RIF", " RAZON SOCIAL","DIRECCION","CONTACTO"};
    String [] registros = new String [4];
           model= new DefaultTableModel(null,titulos);
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT rif_codigo,razon_social,direccion,contacto FROM cliente");
                while(rs.next()){
                    registros[0]= rs.getString("rif_codigo");
                    registros[1]=rs.getString("razon_social");
                     registros[2]=rs.getString("direccion");
                      registros[3]=rs.getString("contacto");
                     model.addRow(registros);
                                }
                tablaClientes.setModel(model);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
               catch( java.lang.NumberFormatException e)
               {
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
    }
 
 
 public Usuario BuscarUsuario( String usu, String contra)
 {
              conectar cc = new conectar();
                Connection cn = cc.conexion();
                try {
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT nombre_completo,aplicacion from usuarios Where usuario='"+usu+"' AND clave='"+contra+"'");
                    while(rs.next()){
                        String nombre = rs.getString("nombre_completo");
                        int app= rs.getInt("aplicacion");
                        Usuario uuss= new Usuario(usu, nombre, contra, app);
                         return uuss;
                       
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
               catch( java.lang.NumberFormatException e)
               {
                   System.out.println("eror en buscarUsuario "+e.toString());
               }  
              
              
          return null;}
 
 public ArrayList ListarNombresClientes(){
   
            ArrayList keywords = new ArrayList<String>();
             
             
            conectar cc = new conectar();
            Connection cn = cc.conexion();
           
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT razon_social FROM cliente");
                int i=0;
                while(rs.next()){
                    keywords.add(rs.getString("razon_social"));
                }
            }     
            catch( Exception e){
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
            
            return keywords;
         } 
 
    public ArrayList ListarIDClientes(){
   
            ArrayList keywords = new ArrayList<String>();
             
             
            conectar cc = new conectar();
            Connection cn = cc.conexion();
           
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT ID FROM cliente");
                int i=0;
                while(rs.next()){
                    keywords.add(rs.getString("ID"));
                }
            }     
            catch( Exception e){
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
            
            return keywords;
         } 
    
 }

 

