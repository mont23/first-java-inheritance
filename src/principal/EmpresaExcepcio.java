package principal;

/**
 *
 * @author Montse Co
 */
public class EmpresaExcepcio extends Exception {

    private String codiCausa = "desconegut";
    private String missatge;

    public EmpresaExcepcio(String codiCausa) {
        this.codiCausa = codiCausa;
        switch (codiCausa) {
            case "1":
                this.missatge = "L'opció ha de ser correcta";
                break;
            case "2":
                this.missatge = "Ja no hi caben més elements";
                break;
            case "3":
                this.missatge = "El codi està repetit";
                break;
            case "GestorXML.model":
                this.missatge = "No s'ha pogut crear el model XML per desar l'empresa";
                break;
            case "GestorXML.desar":
                this.missatge = "No s'ha pogut desar l'empresa a causa d'error d'entrada/sortida";
                break;
            case "GestorXML.carrega":
                this.missatge = "No s'ha pogut carregar l'empresa a causa d'error d'entrada/sortida";
                break;
            default:
                this.missatge = "Error desconegut";
                break;
        }
    }
    
    public String getMessage(){
        return "El codi de causa " + codiCausa + " ha generat aquest missatge: " + missatge;
    }



}
