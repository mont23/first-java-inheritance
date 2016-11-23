/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import principal.Empresa;
import principal.EmpresaExcepcio;



/**
 *
 * @author MiguelAngel
 */
public class GestorPersistencia {
    private GestorXML gestor;

    public GestorXML getGestor() {
        return gestor;
    }

    public void setGestor(GestorXML gestor) {
        this.gestor = gestor;
    }
        
    

    public void desarEmpresa(String tipusPersistencia, String nomFitxer, Empresa empresa) throws EmpresaExcepcio {
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.desarEmpresa(nomFitxer, empresa);
        }
    }

    public void carregarEmpresa(String tipusPersistencia, String nomFitxer) throws EmpresaExcepcio {
        if (tipusPersistencia.equals("XML")) {
            gestor = new GestorXML();
            gestor.carregarEmpresa(nomFitxer);
        }
    }
}
