/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacén.robotizado;

/**
 *
 * @author Asus
 */
public class Estante {
    private int numero;
    private Caja[] cajas;

    public Estante(int numero, Caja[] cajas) {
        this.numero = numero;
        this.cajas = new Caja[3];
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Caja[] getCajas() {
        return cajas;
    }

    public void setCajas(Caja[] cajas) {
        this.cajas = cajas;
    }
    
}
