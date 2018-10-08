/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Santiago
 */
public class Producto {
    private String nombre;
    private int existencias;
    private double precioPU;
    private ArrayList<Integer[]> estantes=new ArrayList<>();

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

    public ArrayList<Integer[]> getCajas() {
        return estantes;
    } 

    public void setPrecioPU(double precioPU) {
        this.precioPU = precioPU;
    }
    public void ingreso(int cant,int estante, int caja){
        Integer[] nuevo={cant,estante,caja};
        this.estantes.add(nuevo);
        this.existencias+=cant;
    }
    public void venta(int cant,int estante, int caja){
        this.existencias-=cant;
        if(this.estantes.get(0)[0]-cant==0){
            this.estantes.remove(0);
        }else{
            Integer[] key={this.estantes.get(0)[0]-cant,estante,caja};
            this.estantes.add(0, key);
        }
        
    }
}
