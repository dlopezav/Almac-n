/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


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
         Scanner lectura=new Scanner(System.in).useDelimiter("/n"); 
         Scanner ints=new Scanner(System.in);
        int k=0;
        boolean produc=false;
        do{
            
            do{
            System.out.println("Bienvenido, escoja una opcion");
            System.out.println("1.Ingresar nuevo tipo  de producto ");
            System.out.println("2.Añadir existencias a un producto");
            System.out.println("3.Comprar producto");
            k = ints.nextInt();           
            if(k==2||k==3&&produc==false){
                System.out.println("Aun no hay productos en el almacen");
            }
            if(k==1){
                produc = true;
            }
            }while(k>3||produc==false);
            
            switch(k){
                case 1:
                    System.out.println("Ingrese la cantidad de productos que creara: ");
                    int a=ints.nextInt();
                    for(int i=0;i<a;i++){
                        System.out.println("Ingrese el nombre del producto: ");
                        String nombre=lectura.nextLine();
                        System.out.println("Ingrese el precio del producto: ");
                        int cod=ints.nextInt();
                        if(almacen.addProducto(nombre, cod)){
                            System.out.println("Se ha adicionado satisfactoriamente el producto");
                        }else{
                            System.out.println("Problemas al adicionar el producto, ya existe");
                        }
                    }
                    break;
                case 2:
                    ArrayList<Producto> productos=almacen.getProductos();
                    HashMap<Integer,Integer[]> product = new HashMap<>();
                    boolean niu[][]= new boolean[20][3];
                    System.out.println("¿Cuantos productos diferentes ingresara?");
                    int b=ints.nextInt();
                    for(int l=0;l<b;l++){
                    int m=0,n=0,estante=0,caja=0,cantidad =0;
                    System.out.println("Productos existentes: ");
                    for(Producto p: productos){
                        m++;
                        System.out.println(m+". "+p.getNombre());
                    }
                    do{
                    System.out.println("Ingrese el numero del producto: ");
                    n=ints.nextInt();
                    }while(n<1 || n>productos.size());
                    Producto pro=productos.get(n-1);
                    do{
                    System.out.println("Ingrese la cantidad del producto a incluir: ");
                    cantidad=ints.nextInt();
                    }while(cantidad<0||cantidad>7);
                    int v=0,t=0;
                    System.out.println("Los espacios libres son: ");
                    for(int i=0;i<20;i++){
                        for(int j=0;j<3;j++){
                            if(almacen.getEstantes()[i].getCajas()[j].getProducto()!=null){
                                if(pro.equals(almacen.getEstantes()[i].getCajas()[j].getProducto())&&7-almacen.getEstantes()[i].getCajas()[j].getCantidad()>=cantidad &&!niu[i][j]){
                                    System.out.println("El estante donde puede añadir productos es el "+(i+1)+" y la caja es la "+(j+1));
                                j=3;
                                i=20;
                                }
                            }else{
                                if(!niu[i][j])System.out.println("Estante: "+(i+1)+" Caja:"+(j+1));
                            }
                        }
                    }
                    do{
                    System.out.println("Ingrese el estante: ");
                    estante=ints.nextInt()-1;
                    }while(estante>19||estante<0);
                    do{
                    System.out.println("Ingrese la caja: ");
                    caja=ints.nextInt()-1;
                    }while(caja<0||caja>3);
                    niu[estante][caja]=true;
                    Integer[] array=new Integer[3];
                    array[0]=cantidad;
                    array[1]=estante;
                    array[2]=caja;
                    
                    product.put(n-1, array);
                    }
                    almacen.ingresarProductos(product);
                    break;
                case 3:
                    ArrayList<Producto> lista=almacen.getProductos();
                    HashMap<Integer, Integer> pedido = new HashMap<>();
                    int q=0,o=0,r=0;
                    System.out.println("Ingrese su nombre: ");
                    String neim;
                    neim = lectura.nextLine();
                    System.out.println("¿cuantos productos diferentes desea comprar?");
                    int cuantos=ints.nextInt();
                    for (int i = 0; i < cuantos; i++) {
                        System.out.println("Productos disponibles: ");
                        for(Producto s: lista){
                            q++;
                            if(s.getExitencias()!=0){
                                System.out.println(q+". "+s.getNombre());
                            }                            
                        }
                        do{
                        System.out.println("Ingrese el numero del producto que se desea comprar: ");
                        o=ints.nextInt()-1;
                        if(o<0 || o>lista.size()||lista.get(o).getExitencias()==0)System.out.println("producto invalido");
                        }while(o<0 || o>lista.size()||lista.get(o).getExitencias()==0);
                        do{
                        System.out.println("Ingrese la cantidad de productos que se desea comprar: ");
                        r=ints.nextInt();
                        if(r>almacen.getProductos().get(o).getExitencias()){
                            System.out.println("No hay suficientes productos, ingrese nuevamente");
                        }
                        }while(r<1 || r>almacen.getProductos().get(o).getExitencias());
                        
                        pedido.put(o, r);
                    }
                    System.out.println(almacen.venta(neim, pedido).toString());
                    
                break;
            }
            
        }while(k>=0 && k<=4);
    }
    
}
