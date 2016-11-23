/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventariable;


import java.util.Scanner;
import principal.Actiu;

/**
 *
 * @author Montse Co
 */
public class Moble implements Actiu{

    private String code;
    private String name;
    private Integer alc;
    private Integer ample;
    private Integer fons;

    public Moble(String code, String name, Integer alc, Integer ample, Integer fons) {
        this.code = code;
        this.name = name;
        this.alc = alc;
        this.ample = ample;
        this.fons = fons;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAlc() {
        return alc;
    }

    public void setAlc(Integer alc) {
        this.alc = alc;
    }

    public Integer getAmple() {
        return ample;
    }

    public void setAmple(Integer ample) {
        this.ample = ample;
    }

    public Integer getFons() {
        return fons;
    }

    public void setFons(Integer fons) {
        this.fons = fons;
    }

    public static Moble addMoble() {
        Scanner teclado = new Scanner(System.in);
        String code;
        String name;
        Integer alc;
        Integer ample;
        Integer fons;
        System.out.println("Codi del moble:");
        code = teclado.next();
        System.out.println("Nom del moble:");
        name = teclado.next();
        System.out.println("Alçada:");
        alc = teclado.nextInt();
        System.out.println("Amplada:");
        ample = teclado.nextInt();
        System.out.println("Fons:");
        fons = teclado.nextInt();
        return new Moble(code, name, alc, ample, fons);
    }

    @Override
    public void updateActiu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nCodi del moble: " + this.getCode());
        System.out.println("\nEntra el nou codi:");
        this.setCode(teclado.next());
        System.out.println("\nNom del moble: " + this.getName());
        System.out.println("\nEntra el nou nom:");
        this.setName(teclado.next());
        System.out.println("\nAlçada: " + this.getAlc());
        System.out.println("\nEntra la nova alçada:");
        this.setAlc(teclado.nextInt());
        System.out.println("\nAmplada: " + this.getAmple());
        System.out.println("\nEntra la nova amplada:");
        this.setAmple(teclado.nextInt());
        System.out.println("\nFons: " + this.getFons());
        System.out.println("\nEntra el nou fons:");
        this.setFons(teclado.nextInt());
        
    }

    @Override
    public void showActiu() {
        System.out.println("\nLes dades del moble amb codi " + this.getCode() + " són:");
        System.out.println("\nNom: " + this.getName());
        System.out.println("\nAlçada: " + this.getAlc());
        System.out.println("\nAmplada: " + this.getAmple());
        System.out.println("\nFons: " + this.getFons());
        
    }

}
