/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariable;

import java.util.Scanner;

/**
 *
 * @author Montse Co
 */
public class Ordinador extends Maquinari{

    
    private Integer velocitat;
    

    public Ordinador(String codi, String descripcio, String marca, Integer memoria, Integer velocitat) {
        super(codi,descripcio,marca,memoria);
        this.velocitat = velocitat;
    }

    

    public Integer getVelocitat() {
        return velocitat;
    }

    public void setVelocitat(Integer velocitat) {
        this.velocitat = velocitat;
    }


    
    public static Ordinador addOrdinador() {
        Scanner teclado = new Scanner(System.in);
        String codiOrdinador;
        String descripcioOrdinador;
        String marcaOrdinador;
        Integer memoriaOrdinador;
        Integer velocitatOrdinador;
        System.out.println("Codi del ordinador:");
        codiOrdinador = teclado.next();
        System.out.println("Descripció del ordinador:");;
        descripcioOrdinador = teclado.next();
        System.out.println("Marca del ordinador:");
        marcaOrdinador = teclado.next();
        System.out.println("Memòria:");
        memoriaOrdinador = teclado.nextInt();
        System.out.println("Velocitat:");
        velocitatOrdinador = teclado.nextInt();
        return new Ordinador(codiOrdinador, descripcioOrdinador, marcaOrdinador, memoriaOrdinador, velocitatOrdinador);
    }

    
    public void updateActiu() {
        super.updateActiu();
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nVelocitat: " + this.getVelocitat());
        System.out.println("\nEntra la nova velocitat:");
        this.setVelocitat(teclado.nextInt());
    }

    public void showActiu() {
        super.showActiu();
        System.out.println("\nVelocitat:" + this.getVelocitat());
    }

}
