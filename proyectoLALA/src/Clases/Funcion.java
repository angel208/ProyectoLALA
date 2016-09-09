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
 
public void listarProductosOV(JTable DetallesOrdenTable, String idOrden ){
     
         DefaultTableModel model;
         String [] titulos={ "Codigo del Producto" , "Codigo Lote" , "Cantidad" };
         String [] registros = new String [3];
     
            model= new DefaultTableModel(null,titulos);
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            
            try {
                Statement st = cn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT codigo_producto, lote_producto, cantidad FROM orden_x_lote WHERE Orden_id = "+idOrden);
                
                while(rs.next()){
                      
                        registros[0]=rs.getString("codigo_producto");                    
                        registros[1]=rs.getString("lote_producto");
                        registros[2]=rs.getString("cantidad");

                        model.addRow(registros);                          
                  
                }
                DetallesOrdenTable.setModel(model);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                }
               catch( java.lang.NumberFormatException e)
               {
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
     
}
     
public void listarOrdenes(JTable tablaOrdenes)
    {
     DefaultTableModel model;
     String [] titulos={"ID", "Cliente","Rif","Direccion","Fecha y hora", "Operadora"};
     String [] registros = new String [6];
     
            model= new DefaultTableModel(null,titulos);
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            
            try {
                Statement st = cn.createStatement();
                
                ResultSet rs = st.executeQuery(
                        
                          "SELECT orden_venta.id, orden_venta.cliente_rif , orden_venta.cliente_direccion , orden_venta.Hora, orden_venta.Despachado, usuarios.nombre_completo, cliente.razon_social "
                        + "FROM orden_venta, usuarios, cliente "
                        + "WHERE usuarios.id = orden_venta.operadora_id "
                        + "AND orden_venta.cliente_rif = cliente.rif_codigo  "
                        //+ "AND orden_venta.cliente_direccion= cliente.direccion "
                        
                        
                );
                
                while(rs.next()){
                    
                  if ( rs.getInt("despachado") == 0){  
                      
                        registros[0]=rs.getString("orden_venta.id");                    
                        registros[1]=rs.getString("cliente.razon_social");
                        registros[2]=rs.getString("orden_venta.cliente_rif");
                        registros[3]=rs.getString("orden_venta.cliente_direccion");
                        registros[4]=rs.getString("orden_venta.Hora");
                        registros[5]=rs.getString("usuarios.nombre_completo");
                        model.addRow(registros);
                        
                  }    
                  
                }
                tablaOrdenes.setModel(model);
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
    
    public void MarcarComoFacturado(String id){
        
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            try {
                Statement st = cn.createStatement();
                st.executeUpdate("UPDATE orden_venta SET despachado = 1 WHERE id = " + id );

            } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
            }
            catch( java.lang.NumberFormatException e) {
                   System.out.println("error producido en void listarCliente"+e.toString());
            }
    }
 
    
    public void LLenarListaProductos(JTable tabla1, JTable tabla2)
    {
     DefaultTableModel model;
     String [] titulos={"Codigo", "Cantidad"};
     String [] registros = new String [2];
     
     model= new DefaultTableModel(null,titulos);
   
                        registros[0]="7591873001131";                    
                        registros[1]="123";
                        model.addRow(registros);
                        registros[0]="7592285000538";                    
                        registros[1]="342";
                        model.addRow(registros);
                        registros[0]="8470007260677";                    
                        registros[1]="43";
                        model.addRow(registros);
                        registros[0]="000014";                    
                        registros[1]="1000";
                        model.addRow(registros);
                        tabla1.setModel(model);
                    
      DefaultTableModel model2;
      String [] titulos2={"Codigo", "Cantidad"};
      String [] registros2 = new String [2];
     
     model2= new DefaultTableModel(null,titulos2);
   
                        registros2[0]="7591873001131";                    
                        registros2[1]="100";
                        model2.addRow(registros2);
                        registros2[0]="7592285000538";                    
                        registros2[1]="45";
                        model2.addRow(registros2);
                        registros2[0]="8470007260677";                    
                        registros2[1]="20";
                        model2.addRow(registros2);
                        tabla2.setModel(model2);            
    }
   
    

    
 }

 
