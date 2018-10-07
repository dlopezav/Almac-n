/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import java.util.HashMap;

/**
 *
 * @author Santiago
 */
public class Producto {
    private String nombre;
    private int existencias;
    private double precioPU;
    private HashMap<Integer, Integer> estantes;

    public Producto(String nombre, double precioPU) {
        this.nombre = nombre;
        this.existencias = 0;
        this.precioPU = precioPU;
    }

    public String getNombre() {
        return nombre;
    }

    public int getExitencias() {
        return existencias;
    }

    public double getPrecioPU() {
        return precioPU;
    }

    public HashMap<Integer, Integer> getCajas() {
        return estantes;
    }

    public void setPrecioPU(double precioPU) {
        this.precioPU = precioPU;
    }
    public void ingreso(int cant, int caja){
        this.estantes.put(caja, estantes.getOrDefault(caja,0)+cant);
        this.existencias+=cant;
    }
    public void venta(int cant, int caja){
        if(this.estantes.get(caja)-cant==0){
            this.estantes.remove(caja);
        }else{
            this.estantes.put(caja, estantes.get(caja)-cant);
        }
        
    }
}
