package inventariable;

import java.util.Scanner;

/**
 *
 * @author Montse Co
 */
public class Impressora extends Maquinari{


    private Integer tipus;

    public Integer getTipus() {
        return tipus;
    }

    public void setTipus(Integer tipus) {
        this.tipus = tipus;
    }
    

    public Impressora(String codi, String descripcio, String marca, Integer memoria, Integer tipus) {
        super(codi,descripcio,marca,memoria);
        this.tipus = tipus;
    }

    
    public static Impressora addImpressora() {
        Scanner teclado = new Scanner(System.in);
        String codiImpressora;
        String descripcioImpressora;
        String marcaImpressora;
        Integer memoriaImpressora;
        Integer tipusImpressora;
        System.out.println("Codi del impressora:");
        codiImpressora = teclado.next();
        System.out.println("Descripció del impressora:");
        descripcioImpressora = teclado.next();
        System.out.println("Marca de la impressora:");
        marcaImpressora = teclado.next();
        System.out.println("Memòria:");
        memoriaImpressora = teclado.nextInt();
        System.out.println("Tipus:");
        tipusImpressora = teclado.nextInt();
        return new Impressora(codiImpressora, descripcioImpressora, marcaImpressora, memoriaImpressora, tipusImpressora);
    }

    
    public void updateActiu() {
        super.updateActiu();
        Scanner teclado = new Scanner(System.in);
        System.out.println("\nTipus: " + this.getTipus());
        System.out.println("\nEntra el nou tipus:");
        this.setTipus(teclado.nextInt());
    }

    public void showActiu() {
        super.showActiu();
        System.out.println("\nTipus:" + this.getTipus());
    }

}
