/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import java.util.HashMap;


/**
 *
 * @author Asus
 */
public class AlmacénRobotizado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        HashMap<Integer,Integer> pedido=new HashMap<>();
        almacen.addProducto("Shampo",15000);
        almacen.addProducto("crema",4500);
        almacen.addProducto("cepillo",3000);
        almacen.addProducto("jabon",5000);
        Integer i=1;
        for (int j = 0; j < 4; j++) {
            pedido.put(j, i++);
        }        
        
        almacen.ingresarProductos(pedido);
        
    }
    
}
