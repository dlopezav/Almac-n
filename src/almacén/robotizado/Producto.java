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
    private HashMap<Integer[], Integer> estantes=new HashMap<>();

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

    public HashMap<Integer[], Integer> getCajas() {
        return estantes;
    }

    public void setPrecioPU(double precioPU) {
        this.precioPU = precioPU;
    }
    public void ingreso(int cant,int estante, int caja){
        Integer[] nuevo={estante,caja};
        this.estantes.put(nuevo, estantes.getOrDefault(nuevo,0)+cant);
        this.existencias+=cant;
    }
    public void venta(int cant,int estante, int caja){
        Integer[] key={estante,caja};
        if(this.estantes.get(key)-cant==0){
            this.estantes.remove(key);
        }else{
            this.estantes.put(key, estantes.get(key)-cant);
        }
        
    }
}
