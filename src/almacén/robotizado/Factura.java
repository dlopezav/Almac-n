/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Factura {
   private String cliente;
   private double precio=0;
   private HashMap<Producto, Integer> productos;

    public Factura(String cliente) {
        this.cliente = cliente;
        this.productos = new HashMap<>();
    }

    public HashMap<Producto, Integer> getProductos() {
        return productos;
    }

    public boolean addProducto(Producto producto, int a) {
        this.precio+=producto.getPrecioPU()*a;
        return productos.put(producto,a)==null;
    }
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String toString(){
        double c=0;
       String str = "\n AMAZON STORE \n";
       str+="\n CLIENTE: "+this.cliente+"\n";
       str+="Lista de productos comprados: ";
       for(Map.Entry<Producto, Integer> it: productos.entrySet()){
            str+="\n - "+it.getKey().getNombre()+" cant: "+it.getValue()+" precio total: "+ it.getKey().getPrecioPU()*it.getValue();
       }
       str+="\t TOTAL A PAGAR: "+ this.precio;
       return str;
    }
}
