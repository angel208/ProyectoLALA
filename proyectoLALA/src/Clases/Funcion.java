/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
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
         String [] titulos={ "lote" , "Codigo Producto" , "Cantidad" };
         String [] registros = new String [3];
     
            model= new DefaultTableModel(null,titulos) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            
            try {
                Statement st = cn.createStatement();
                
                ResultSet rs = st.executeQuery("SELECT correlativo_lote, codigo_producto, cantidad FROM orden_x_lote WHERE Orden_id = "+idOrden);
                
                while(rs.next()){
                      
                        registros[0]=rs.getString("correlativo_lote");                    
                        registros[1]=rs.getString("codigo_producto");
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
     
            model= new DefaultTableModel(null,titulos) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
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
                    ResultSet rs = st.executeQuery("SELECT nombre_completo,aplicacion,id from usuarios Where usuario='"+usu+"' AND clave='"+contra+"'");
                    while(rs.next()){
                        String nombre = rs.getString("nombre_completo");
                        int app= rs.getInt("aplicacion");
                        int id = rs.getInt("id");
                        Usuario uuss= new Usuario(usu, nombre, contra, app, id);
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
                int cant =0 ;
                
                
                //restar de la reserva       
                st.executeUpdate("UPDATE productos_reservados "
                               + "INNER JOIN ( SELECT codigo_producto, SUM(cantidad) as cantidad "
                                            + "FROM orden_x_lote as OL "
                                            + "WHERE OL.orden_id = " +id+" "
                                            + "GROUP BY codigo_producto "
                               + ") AS Result ON ( Result.codigo_producto = productos_reservados.codigo_producto ) "
                               + "SET productos_reservados.cantidad_reservada = productos_reservados.cantidad_reservada - Result.cantidad "

                );        
      
                //restar de la tabla de lotes          
                st.executeUpdate("UPDATE lote_producto "
                               + "INNER JOIN orden_x_lote ON ( Orden_x_lote.correlativo_lote =  lote_producto.correlativo "
                               + "AND Orden_x_lote.Orden_id = "+ id + " )"
                               + "SET  lote_producto.cantidad_x_lote =  lote_producto.cantidad_x_lote - orden_x_lote.cantidad "

                ); 
                
                st.executeUpdate("UPDATE orden_venta SET despachado = 1 WHERE id = " + id );

            } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
            }
            catch( java.lang.NumberFormatException e) {
                   System.out.println("error producido en void listarCliente"+e.toString());
            }
    }
 
public void InsertarDemanda( JTable tabla){
                         
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            String FechaActual;
           
            try {
                
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
                FechaActual = sdf.format(cal.getTime());
                
                Statement st = cn.createStatement();

                for (int count = 0; count < ((DefaultTableModel)tabla.getModel()).getRowCount(); count++){
                   
                   String producto = ((DefaultTableModel)tabla.getModel()).getValueAt(count, 0).toString(); //cod
                   String cantidad = ((DefaultTableModel)tabla.getModel()).getValueAt(count, 1).toString(); //cant 
                   ResultSet rs = st.executeQuery("SELECT * FROM estadisticas_demanda_productos WHERE codigo_producto = "+producto+" AND fecha = '"+FechaActual+"'");
                    System.out.println("SELECT * FROM estadisticas_demanda_productos WHERE codigo_producto = "+producto+" AND fecha = "+FechaActual);
                  
                   if ( !rs.next() ){
                       
                        PreparedStatement MSQL_statement = cn.prepareStatement("INSERT INTO estadisticas_demanda_productos ( Codigo_producto , cantidad , fecha ) VALUES (?,?,?)");

                        MSQL_statement.setString(1, producto);
                        MSQL_statement.setString(2, cantidad);
                        MSQL_statement.setString(3, FechaActual);
                        
                        System.out.println(MSQL_statement);
                        MSQL_statement.executeUpdate();
              
                   }
                   else {
                         System.out.println("updateando: "+ producto);
                        st.executeUpdate("UPDATE estadisticas_demanda_productos SET cantidad = cantidad + "+cantidad+" WHERE Codigo_producto = "+producto +" AND fecha = '"+FechaActual+"'");
                        
                   }
                   
                }
            }     
            catch( Exception e){
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
            
          
} 

public void CrearRegistroLlamada(String usuario, String riff, String direccion, String idCliente, int exitosa, Calendar cal){
                         
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            String FechaActual;
            String HoraActual;
           
            try {

                SimpleDateFormat sdfFecha = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
                
                FechaActual = sdfFecha.format(cal.getTime());
                HoraActual = sdfHora.format(cal.getTime());
                
                System.out.println(FechaActual + " " + HoraActual);
                       
                PreparedStatement MSQL_statement = cn.prepareStatement(
                        "INSERT INTO registro_llamadas ( usuario , id_cliente , rif_codigo, direccion, fecha_llamada, hora_llamada, exitosa ) VALUES (?,?,?,?,?,?,?)");

                MSQL_statement.setString(1, usuario);
                MSQL_statement.setString(2, idCliente);
                MSQL_statement.setString(3, riff);
                MSQL_statement.setString(4, direccion);
                MSQL_statement.setString(5, FechaActual);
                MSQL_statement.setString(6, HoraActual);
                MSQL_statement.setInt   (7, exitosa);
                        
                System.out.println(MSQL_statement);
                MSQL_statement.executeUpdate();
     
            }     
            catch( Exception e){
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
            
          
} 

public void ReservarProducto(String CodigoProducto, int Cantidad){
                         
            conectar cc = new conectar();
            Connection cn = cc.conexion();
           
            try {
                
                Statement st = cn.createStatement();

                    ResultSet rs = st.executeQuery("SELECT * FROM productos_reservados WHERE codigo_producto = "+CodigoProducto);
                  
                   if ( !rs.next() ){
                       
                        PreparedStatement MSQL_statement = cn.prepareStatement("INSERT INTO productos_reservados ( Codigo_producto , cantidad_reservada ) VALUES (?,?)");

                        MSQL_statement.setString(1, CodigoProducto);
                        MSQL_statement.setInt(2, Cantidad);
   
                        System.out.println(MSQL_statement);
                        MSQL_statement.executeUpdate();
              
                   }
                   else {

                        st.executeUpdate("UPDATE productos_reservados SET cantidad_reservada = cantidad_reservada + "+Cantidad+" WHERE Codigo_producto = "+CodigoProducto);
                        
                   }
                   
                
            }     
            catch( Exception e){
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
            
            
          
} 

public void InsertarLotexOrden(int IDorden, String Producto, Reserva R, int cantidad ){
                         
            conectar cc = new conectar();
            Connection cn = cc.conexion();
           
            try {
                       
                PreparedStatement MSQL_statement = cn.prepareStatement(
                        "INSERT INTO orden_x_lote ( correlativo_lote, Orden_id , codigo_producto ,lote_producto , cantidad ) VALUES (?,?,?,?,?)");
                
                MSQL_statement.setString(1, R.getCorrelativo());
                MSQL_statement.setInt(2, IDorden);
                MSQL_statement.setString(3, Producto );
                MSQL_statement.setString(4, R.getLote());
                MSQL_statement.setInt(5, cantidad);

                        
                System.out.println(MSQL_statement);
                MSQL_statement.executeUpdate();
     
            }     
            catch( Exception e){
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
            
          
} 

public Reserva[] ObtenerListaDeLotes(String IDproducto ) {
    
     
            ArrayList Lista = new ArrayList<Reserva>();
                
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            
            try {
                Statement st = cn.createStatement();
                
                ResultSet rs = st.executeQuery(                        
                          "SELECT * "
                        + "FROM lote_producto as LP, orden_x_lote as OL, orden_venta as OV  "
                        + "WHERE OV.despachado = 0 "
                        + "AND LP.codigo_producto = "+IDproducto+" "
                        + "AND OL.orden_id = OV.id " 
                        + "AND OL.correlativo_lote = LP.correlativo " 
                );
                
                if (!rs.next()){
                    
                       rs = st.executeQuery(                        
                          "SELECT correlativo as codigo_lote, fecha_ingreso, cantidad_x_lote as cantidad, lote_producto as lote  "
                        + "FROM lote_producto as LP "
                        + "WHERE LP.codigo_producto = "+IDproducto+" "
                        ); 
                    
                }else{                
                    
                    rs = st.executeQuery(
                            
                            

                                  "SELECT LP.correlativo as codigo_lote, LP.lote_producto as lote, LP.fecha_ingreso, (LP.cantidad_x_lote - SUM(OL.cantidad) ) as cantidad "
                                + "FROM lote_producto as LP, orden_x_lote as OL, orden_venta as OV  "
                                + "WHERE OV.despachado = 0 "
                                + "AND LP.codigo_producto = "+IDproducto+" "
                                + "AND OL.orden_id = OV.id "
                                + "AND OL.correlativo_lote = LP.correlativo "
                                + "GROUP BY codigo_lote, fecha_ingreso, LP.lote_producto "
                                          
                                + "UNION ALL "
                                          
                                + "SELECT LP.correlativo as codigo_lote, LP.lote_producto as lote, LP.fecha_ingreso, LP.cantidad_x_lote as cantidad "
                                + "FROM lote_producto as LP LEFT JOIN orden_x_lote AS OL ON OL.correlativo_lote = LP.correlativo "
                                + "WHERE  OL.correlativo_lote IS NULL "
                                + "AND LP.codigo_producto = " +IDproducto+" "
                    );
                    
                    
                    
                }                    
               
                
                while(rs.next()){
                   
                        Lista.add( new Reserva( rs.getString("codigo_lote"), rs.getInt("cantidad"), rs.getString("lote"), rs.getString("fecha_ingreso") ) );
   
                }
                
                Reserva[] ListaProductos = new Reserva[Lista.size()];
                
                ListaProductos = (Reserva[]) Lista.toArray( ListaProductos);
                
                Arrays.sort(ListaProductos);    
               
		for(Reserva temp: ListaProductos){
		   System.out.println("lote: " + temp.getCorrelativo() + " - " + temp.getFecha() + " - cantidad: "+temp.getCantidad());
		}
                
                return ListaProductos;
                
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                    return null;
                }
               catch( java.lang.NumberFormatException e)
               {
                   System.out.println("error producido en void listarCliente"+e.toString());
                   return null;
               }
}



public void Despachar(int IDorden, JTable tabla){
    
     for (int count = 0; count < ((DefaultTableModel)tabla.getModel()).getRowCount(); count++){
         

         String Producto = ((DefaultTableModel)tabla.getModel()).getValueAt(count, 0).toString();
         
         int cant;
         
        try{
            
           cant = Integer.parseInt(((DefaultTableModel)tabla.getModel()).getValueAt(count, 1).toString());
           
        }catch(java.lang.NumberFormatException e){
           if ( ((DefaultTableModel)tabla.getModel()).getValueAt(count, 1).toString().equals("") )  break;
           else if ( ((DefaultTableModel)tabla.getModel()).getValueAt(count, 1).toString().equals(" ") ) break;
           else { 
                showMessageDialog(tabla, "El producto "+Producto+" no pudo ser procesado por un error en la cantidad.\n por favor verifique que la cantidad especificada solo contiene numeros.");
                break;
           }
        }
        
        int i = 0;
         
         if( !Producto.equals("")){

                Reserva R[] = ObtenerListaDeLotes( Producto );

               System.out.println(R.length);

                for(Reserva temp: R){

                          System.out.println("lote: " + temp.getCorrelativo() + " - " + temp.getFecha() + " - cantidad: "+temp.getCantidad());
                }

                while( cant > 0){

                    if( R[i].getCantidad() <= cant ){
                        
                        if (R[i].getCantidad() != 0){
                            cant = cant - R[i].getCantidad();
                            InsertarLotexOrden( IDorden, Producto,  R[i], R[i].getCantidad() );
                            ReservarProducto(Producto, R[i].getCantidad());
                        }

                    }
                    else{


                        InsertarLotexOrden( IDorden, Producto,  R[i], cant );
                        ReservarProducto(Producto, cant);
                        cant = 0;

                    }


                 i++;   
                }

       } 
     }
    
}


public int CrearOrdenVenta( int usr , String rif, String direccion){
    
            int ID=0;
            conectar cc = new conectar();
            Connection cn = cc.conexion();
            String HoraActual;
           
            try {
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE, 30);
                HoraActual = sdf.format(cal.getTime());
                java.sql.Timestamp HA = new java.sql.Timestamp(sdf.parse(HoraActual).getTime());
                
                
                PreparedStatement MSQL_statement = cn.prepareStatement(
                        "INSERT INTO orden_venta ( hora , operadora_id , cliente_rif, cliente_direccion, despachado ) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

                MSQL_statement.setTimestamp(1, HA );
                MSQL_statement.setInt(2, usr);
                MSQL_statement.setString(3, rif);
                MSQL_statement.setString(4, direccion);
                MSQL_statement.setInt(5, 0);

                        
                MSQL_statement.executeUpdate();
     
                ResultSet rs = MSQL_statement.getGeneratedKeys();
                rs.next();
                ID = rs.getInt(1);
            }     
            catch( Exception e){
                   System.out.println("error producido en void listarCliente"+e.toString());
               }
          
          return ID;
}

public void LLenarListaProductos(JTable tabla1, JTable tabla2)
    {
     DefaultTableModel model;
     String [] titulos={"Codigo", "Cantidad"};
     String [] registros = new String [2];
     
     model= new DefaultTableModel(null,titulos);
   
                        registros[0]="7594001101451";                    
                        registros[1]="100";
                        model.addRow(registros);
                        registros[0]="7594001101017";                    
                        registros[1]="200";
                        model.addRow(registros);
                        registros[0]="7594001101048";                    
                        registros[1]="50";
                        model.addRow(registros);
                        registros[0]="7592637006775";                    
                        registros[1]="1000";
                        model.addRow(registros);
                        tabla1.setModel(model);
                    
      DefaultTableModel model2;
      String [] titulos2={"Codigo", "Cantidad"};
      String [] registros2 = new String [2];
     
     model2= new DefaultTableModel(null,titulos2);
   
                        registros2[0]="7594001101451";                    
                        registros2[1]="1";
                        model2.addRow(registros2);
                        registros2[0]="7594001101017";                    
                        registros2[1]="2";
                        model2.addRow(registros2);
                        registros2[0]="7594001101048";                    
                        registros2[1]="1";
                        model2.addRow(registros2);
                        tabla2.setModel(model2);            
    }
   
    

    
 }

 
