/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import becker.robots.*;

/**
 *
 * @author Asus
 */
public class Almacen {
    private City ciudad;
    private Robot[] empleado;
    private Estante[] estantes;
    private Thing[] stand;
    
    
    public Almacen(){
        this.ciudad=new City(0, 0, 12, 12);
        for(int i=1;i<8;i++){
            Wall pared=new Wall(ciudad,i,4,Direction.WEST);
            pared=new Wall(ciudad,i,3,Direction.EAST);
        } 
        for(int i=1;i<11;i++){
            Wall pared=new Wall(ciudad,1,i,Direction.NORTH);
            if(i<10){
                pared=new Wall(ciudad,i,1,Direction.WEST);
                pared=new Wall(ciudad,i,10,Direction.EAST);
                pared=new Wall(ciudad,i,10,Direction.EAST);
            }
        }
         for(int i=0;i<4;i++){
            Wall pared=new Wall(ciudad,9,i+1,Direction.SOUTH);
            pared = new Wall(ciudad,9,i+7,Direction.SOUTH);
        }
         this.empleado= new Robot[8];
         this.estantes =  new Estante[20];
         for (int i = 0; i < 20; i++) {
            this.estantes[i]=new Estante(i);
        }
         this.stand =new Thing[20];
         for (int i = 0; i < 8; i++) {
            this.empleado[i]=new Robot(ciudad,8-i,2,Direction.EAST);
        }
         for (int i = 6; i>0; i--) {
            stand[6-i]=new Thing(ciudad,i+1,4);
            stand[6-i].getIcon().setLabel("Estante"+String.valueOf(7-i));
            if(i>1){
            stand[11-i]=new Thing(ciudad,1,11-i);
            stand[11-i].getIcon().setLabel("Estante"+String.valueOf(13-i));
            }
            stand[17-i]=new Thing(ciudad,8-i,10);
            stand[17-i].getIcon().setLabel("Estante"+String.valueOf(18-i));
        }
        stand[17]=new Thing(ciudad,8,10);
        stand[17].getIcon().setLabel("Estante"+String.valueOf(18));

        stand[18]=new Thing(ciudad,9,9);
        stand[18].getIcon().setLabel("Estante"+String.valueOf(19));
        stand[19]=new Thing(ciudad,9,8);
        stand[19].getIcon().setLabel("Estante"+String.valueOf(20));            
    }
    public boolean ingresarProductos(String producto, int cant){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.estantes[i].getCajas()[j]!=null||(this.estantes[i].getCajas()[j].getProducto().equals(producto)&&this.estantes[i].getCajas()[j].getCantidad()<7)){
                    int max=7-this.estantes[i].getCajas()[j].getCantidad();
                    if(cant<max){
                        this.estantes[i].getCajas()[j].setCantidad(this.estantes[i].getCajas()[j].getCantidad()+cant);
                        return true;
                    }else{
                        cant=cant-max;
                        this.estantes[i].getCajas()[j].setCantidad(7);
                    }
                    
                }else{
                    if(this.estantes[i].getCajas()[j]==null){
                        if(cant<7){
                            this.estantes[i].addCaja(new Caja(cant,producto));
                            
                            return true;
                        }else{
                            this.estantes[i].addCaja(new Caja(7,producto));
                            cant-=7;
                        }
                        
                    }
                }
            }
        }
        return false;
    }
}
