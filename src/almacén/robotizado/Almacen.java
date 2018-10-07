/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import becker.robots.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Asus
 */
public class Almacen {
    private City ciudad;
    private Robot[] empleado;
    private Estante[] estantes;
    private Thing[] stand;
    private ArrayList<Producto> productos=new ArrayList<>();
    private int robot=0;

    public boolean addProducto(String nombre, int precioPU) {
        for(Producto p: productos){
            if(p.getNombre().equals(nombre)){
                return false;
            }else{
                productos.add(new Producto(nombre,precioPU));
            }
        }
        return true;
    }
    public void deleteProducto(Producto producto){
        this.productos.remove(producto);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }
       
    public Almacen(){
        this.ciudad=new City(0, 0, 11, 14);
        for(int i=1;i<8;i++){
            Wall pared=new Wall(ciudad,i,4,Direction.WEST);
            pared=new Wall(ciudad,i,3,Direction.EAST);
        } 
        for(int i=1;i<13;i++){
            Wall pared=new Wall(ciudad,1,i,Direction.NORTH);
            if(i<10){
                pared=new Wall(ciudad,i,1,Direction.WEST);
                pared=new Wall(ciudad,i,12,Direction.EAST);
                //pared=new Wall(ciudad,i,10,Direction.EAST);
            }
        }
         for(int i=1;i<9;i++){
            Wall pared=new Wall(ciudad,9,i,Direction.SOUTH); 
        }
        for (int i = 6; i < 11; i++) {
            Wall pared=new Wall(ciudad,3,i,Direction.NORTH);
            pared=new Wall(ciudad,4,i,Direction.NORTH);
            pared=new Wall(ciudad,i-3,6,Direction.WEST);
            pared=new Wall(ciudad,i-3,10,Direction.EAST);
            if(i>6){
                pared=new Wall(ciudad,i-3,9,Direction.WEST);
                pared=new Wall(ciudad,i-3,7,Direction.EAST);
            }
        }
        Wall pared =new Wall(ciudad,7,8,Direction.SOUTH);
         this.empleado= new Robot[8];
         this.estantes =  new Estante[20];
         for (int i = 0; i < 20; i++) {
            this.estantes[i]=new Estante(i);
        }
         this.stand =new Thing[20];
         for (int i = 0; i < 8; i++) {
            this.empleado[i]=new Robot(ciudad,8-i,2,Direction.EAST);
        }
         for (int i = 6; i>=0; i--) {
            if(i>0){
            stand[6-i]=new Thing(ciudad,i+1,4);
            stand[6-i].getIcon().setLabel("Estante"+String.valueOf(7-i));
            }
            stand[13-i]=new Thing(ciudad,1,11-i);
            stand[13-i].getIcon().setLabel("Estante"+String.valueOf(13-i));
            
            stand[19-i]=new Thing(ciudad,8-i,12);
            stand[19-i].getIcon().setLabel("Estante"+String.valueOf(20-i));
        }
         
        
    }
    public boolean ingresarProductos(HashMap<Integer, Integer> pedido){
        pedido.entrySet().forEach((it) -> {
            ingresarProducto(productos.get(it.getKey()),it.getValue());
        });
        this.robot=0;
        return true;
    }
    public boolean ingresarProducto(Producto producto, int cant){
        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.estantes[i].getCajas()[j]!=null){
                    if(this.estantes[i].getCajas()[j].getProducto().equals(producto)&&this.estantes[i].getCajas()[j].getCantidad()<7){
                        int max=7-this.estantes[i].getCajas()[j].getCantidad();
                        if(cant<max){
                            this.estantes[i].getCajas()[j].setCantidad(this.estantes[i].getCajas()[j].getCantidad()+cant);
                            producto.ingreso(cant, i);
                            if(!this.estantes[i].isTomado()){
                                if(robot<8){
                                    ingresar(empleado[robot++],i);
                                }
                            }
                            return true;
                        }else{
                            cant=cant-max;
                            this.estantes[i].getCajas()[j].setCantidad(7);
                            if(!this.estantes[i].isTomado())ingresar(empleado[robot++],i);
                        }
                    }  
                }else{
                    if(this.estantes[i].getCajas()[j]==null){
                        String nuevo="";
                        if(cant<7){
                            this.estantes[i].addCaja(new Caja(cant,producto));
                            if(!this.estantes[i].isTomado())ingresar(empleado[robot++],i);
                            System.out.println(robot);
                            for (int k = 0; k < 3; k++) {
                                if(this.estantes[i].getCajas()[k]!=null)nuevo+=this.estantes[i].getCajas()[k].getProducto().getNombre();
                            }                            
                            this.stand[i].getIcon().setLabel(nuevo);
                            return true;
                        }else{
                            this.estantes[i].addCaja(new Caja(7,producto));
                            cant-=7;
                            if(!this.estantes[i].isTomado())ingresar(empleado[robot++],i);
                            
                            for (int k = 0; k < 3; k++) {
                                if(this.estantes[i].getCajas()[k]!=null)nuevo+=this.estantes[i].getCajas()[k].getProducto().getNombre();
                            }                            
                            this.stand[i].getIcon().setLabel(nuevo);
                        }
                        
                    }
                }
            }
        }
        return false;
    }
    private void move(Robot R,int c){
        for (int i = 0; i < c; i++) {
            R.move();
        }  
    }
    private void turn(Robot R,int c){
        for (int i = 0; i < c; i++) {
            R.turnLeft();
        }  
    }
    private void comprovar(Robot R,int i, int c, boolean t){
        if(c==i){
                turn(R,1);
                move(R,1);
                if(t)R.pickThing();
                else R.putThing();
                turn(R,2);
                move(R,1);
                turn(R,1);
            }
    }
    private void recorrer(Robot R, int c,boolean t){
        for (int i = 0; i < 6; i++) {
            move(R,1);
            comprovar(R,i,c,t);            
        }
        turn(R,3);
        for (int i = 6; i < 12; i++) {
            comprovar(R,i,c,t);
            move(R,1);
        }
        comprovar(R,12,c,t);
        turn(R,3);
        for (int i = 13; i < 19; i++) {
            comprovar(R,i,c,t);
            move(R,1);
        }
        comprovar(R,19,c,t);
        turn(R,3);
        
    }
    private void ingresar(Robot R,int c){
        estantes[c].setTomado(true);
        move(R,1);
        turn(R,3);
        move(R,8-R.getStreet());
        turn(R,1);
        move(R,2);
        turn(R,1);
        recorrer(R,c,true);
        move(R,1);
        turn(R,3);
        if(robot<4){
            move(R,4);
            turn(R,1);
            move(R,1);
            turn(R,1);
            move(R,4-robot);
        }else{
            move(R,7-robot);
        }
        
    }
    public void devolver(Robot R,int c){
        for (int i = 0; i < robot; i++) {
            if(i<4)move(empleado[i],i);
            else{
                move(empleado[i],i-4);
                turn(empleado[i],1);
                move(empleado[i],1);
                
            }
            empleado[i].putThing();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
            empleado[i].pickThing();
        }
        R.pickThing();
        turn(R,2);
        move(R,2);
        turn(R,1);
        move(R,1);
        turn(R,3);
        recorrer(R,c,false);
        turn(R,1);
        move(R,1);
        turn(R,3);
        move(R,6);
        turn(R,3);
        move(R,1);
        turn(R,3);
        move(R,1);
        estantes[c].setTomado(false);
    }
}
