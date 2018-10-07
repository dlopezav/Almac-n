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
        HashMap<Integer,Integer[]> pedido=new HashMap<>();
        almacen.addProducto("Shampoo",15000);
        almacen.addProducto("crema",4500);
        almacen.addProducto("cepillo",3000);
        almacen.addProducto("jabon",5000);
        Integer[][] i=new Integer[4][3];
        for (int j = 0; j < 4; j++) {
            
            i[j][0]=j;
            i[j][1]=j;
            i[j][2]=1;
            pedido.put(j, i[j]);
            System.out.println("");
        }   
        
        almacen.ingresarProductos(pedido);
//         Scanner lectura=new Scanner(System.in);
//        int k=0;
//        do{
//            System.out.println("1.Ingresar tipo producto: ");
//            System.out.println("2.Ingresar existencias de un producto:");
//            System.out.println("3.Comprar producto:");
//            
//            k = lectura.nextInt();
//             
//            switch(k){
//                case 1:
//                    System.out.println("Ingrese el nombre del producto: ");
//                    String nombre=lectura.next();
//                    System.out.println("Ingrese el precio del producto: ");
//                    int cod=lectura.nextInt();
//                    
//                    if(almacen.addProducto(nombre, cod)){
//                        System.out.println("Se ha adicionado satisfactoriamente el producto");
//                    }else{
//                        System.out.println("Problemas al adicionar el producto, ya existe");
//                    }
//                    break;
//                case 2:
//                    System.out.println("Ingrese el nombre del proyecto: ");
//                    nombre=lectura.next();             
//                    
//                    if(universidad.adicionarProyecto(nombre)){
//                        System.out.println("Se ha creado satisfactoriamente el proyecto");
//                    }else{
//                        System.out.println("Problemas al realizar la creacion del Proyecto");
//                    }
//                    break;
//                case 3:
//                     HashMap<Integer, Estudiante> estudiantes = 
//                              universidad.listarEstudiantes();
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     for (Estudiante estudiante : estudiantes.values()) {
//                           System.out.println(estudiante.getCodigo()  + " . " + estudiante.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     Estudiante estudiante = universidad.buscarEstudiante(cod);
//                     
//                     System.out.println("Seleccione el proyecto");
//                     ArrayList<Proyecto> proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                    cod =  lectura.nextInt();
//                    
//                    if(estudiante != null){
//                       if(universidad.adicionarEstudianteProyecto(estudiante, cod)){
//                           System.out.println("Se ha adicionado satisfactoriamente el estudiante al proyecto");
//                       }else{
//                           System.out.println("Problemas al adicionar al estudiante en el proyecto");
//                       }
//                    }
//                    
//                    
//                    break;
//                case 4:
//                    
//                    estudiantes = 
//                              universidad.listarEstudiantes();
//                                    
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     
//                    break;
//                case 5:
//                     proyectos = universidad.listarProyectos();
//                     for (int j = 0; j < proyectos.size(); j++) {
//                         System.out.println(j + " " + proyectos.get(j).getNombre());
//                    }
//                     
//                    break;
//                    
//                   
//                case 6:
//                     System.out.println("Buscar estudiante.");
//                     System.out.println("Seleccione el codigo del estudiante");
//                
//                     estudiantes = 
//                              universidad.listarEstudiantes();
//                     
//                     for (Estudiante _est : estudiantes.values()) {
//                           System.out.println(_est.getCodigo()  + " . " + _est.getNombre() );
//                     }
//                     cod = lectura.nextInt();
//                     estudiante = universidad.buscarEstudiante(cod);
//                     
//                     if(estudiante != null){
//                         System.out.println(estudiante.getCodigo() + " " + estudiante.getNombre());
//                         System.out.println("Esta registrado en los siguientes proyectos");
//                     }else{
//                         System.out.println("El estudiante seleccionado no existe");
//                     }                     
//                     break;
//            }
//            
//           
//        }while(k>=0 && i<=4);
    }
    
}
