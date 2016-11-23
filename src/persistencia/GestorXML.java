/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import inventariable.Impressora;
import inventariable.Moble;
import inventariable.Ordinador;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import principal.Actiu;
import principal.Empresa;
import principal.EmpresaExcepcio;
import principal.Nau;

public class GestorXML implements ProveedorPersistencia {

    private Document doc;
    private Empresa empresa;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public void desarEmpresa(String nomFitxer, Empresa empresa) throws EmpresaExcepcio {
        construeixModel(empresa);
        desaModel(nomFitxer);
    }

    @Override
    public void carregarEmpresa(String nomFitxer) throws EmpresaExcepcio {
        carregaFitxer(nomFitxer);
        fitxerEmpresa();
    }

    private void construeixModel(Empresa empresa) throws EmpresaExcepcio {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (Exception ex) {
            Logger.getLogger(GestorXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        doc = builder.newDocument();

        Element arrel = doc.createElement("Empresa");
        arrel.setAttribute("Codi", empresa.getCode().toString());
        arrel.setAttribute("Nom", empresa.getName());
        arrel.setAttribute("Adreça", empresa.getAddress());
        doc.appendChild(arrel);

        for (int i = 0; i < empresa.getComptaActius(); i++) {
            if (empresa.getActius()[i] instanceof Ordinador) {
                Element fill = doc.createElement("Ordinador");
                fill.setAttribute("Codi", ((Ordinador) empresa.getActius()[i]).getCodi());
                fill.setAttribute("Descripcio", ((Ordinador) empresa.getActius()[i]).getDescripcio());
                fill.setAttribute("Marca", ((Ordinador) empresa.getActius()[i]).getMarca());
                fill.setAttribute("Memoria", ((Ordinador) empresa.getActius()[i]).getMemoria().toString());
                fill.setAttribute("Velocitat", ((Ordinador) empresa.getActius()[i]).getVelocitat().toString());
                arrel.appendChild(fill);
            }
            if (empresa.getActius()[i] instanceof Impressora) {
                Element fill = doc.createElement("Impressora");
                fill.setAttribute("Codi", ((Impressora) empresa.getActius()[i]).getCodi());
                fill.setAttribute("Descripcio", ((Impressora) empresa.getActius()[i]).getDescripcio());
                fill.setAttribute("Marca", ((Impressora) empresa.getActius()[i]).getMarca());
                fill.setAttribute("Memoria", ((Impressora) empresa.getActius()[i]).getMemoria().toString());
                fill.setAttribute("Tipus", ((Impressora) empresa.getActius()[i]).getTipus().toString());
                arrel.appendChild(fill);
            }
            if (empresa.getActius()[i] instanceof Moble) {
                Element fill = doc.createElement("Moble");
                fill.setAttribute("Codi", ((Moble) empresa.getActius()[i]).getCode());
                fill.setAttribute("Nom", ((Moble) empresa.getActius()[i]).getName());
                fill.setAttribute("Alçada", ((Moble) empresa.getActius()[i]).getAlc().toString());
                fill.setAttribute("Ample", ((Moble) empresa.getActius()[i]).getAmple().toString());
                fill.setAttribute("Fons", ((Moble) empresa.getActius()[i]).getFons().toString());
                arrel.appendChild(fill);
            }
            if (empresa.getActius()[i] instanceof Nau) {
                Element fill = doc.createElement("Nau");
                fill.setAttribute("Codi", ((Nau) empresa.getActius()[i]).getCode());
                fill.setAttribute("Nom", ((Nau) empresa.getActius()[i]).getName());
                for (int j = 0; j < ((Nau) empresa.getActius()[i]).getActius().size(); j++) {
                    Object preObject = ((Nau) empresa.getActius()[i]).getActius().get(j);
                    if (preObject instanceof Ordinador) {
                        Element subFill = doc.createElement("Ordinador");
                        subFill.setAttribute("Codi", ((Ordinador) preObject).getCodi());
                        subFill.setAttribute("Descripcio", ((Ordinador) preObject).getDescripcio());
                        subFill.setAttribute("Marca", ((Ordinador) preObject).getMarca());
                        subFill.setAttribute("Memoria", ((Ordinador) preObject).getMemoria().toString());
                        subFill.setAttribute("Velocitat", ((Ordinador) preObject).getVelocitat().toString());
                        fill.appendChild(subFill);
                    }
                    if (preObject instanceof Impressora) {
                        Element subFill = doc.createElement("Impressora");
                        subFill.setAttribute("Codi", ((Impressora) preObject).getCodi());
                        subFill.setAttribute("Descripcio", ((Impressora) preObject).getDescripcio());
                        subFill.setAttribute("Marca", ((Impressora) preObject).getMarca());
                        subFill.setAttribute("Memoria", ((Impressora) preObject).getMemoria().toString());
                        subFill.setAttribute("Tipus", ((Impressora) preObject).getTipus().toString());
                        fill.appendChild(subFill);
                    }
                    if (preObject instanceof Moble) {
                        Element subFill = doc.createElement("Moble");
                        subFill.setAttribute("Codi", ((Moble) preObject).getCode());
                        subFill.setAttribute("Nom", ((Moble) preObject).getName());
                        subFill.setAttribute("Alcada", ((Moble) preObject).getAlc().toString());
                        subFill.setAttribute("Ample", ((Moble) preObject).getAmple().toString());
                        subFill.setAttribute("Fons", ((Moble) preObject).getFons().toString());
                        fill.appendChild(subFill);
                    }
                }
                arrel.appendChild(fill);
            }
        }

    }

    private void desaModel(String nomFitxer) throws EmpresaExcepcio {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            File f = new File(nomFitxer + ".xml");
            StreamResult result = new StreamResult(f);
            transformer.transform(source, result);
        } catch (Exception ex) {
            throw new EmpresaExcepcio("GestorXML.desar");
        }
    }

    private void carregaFitxer(String nomFitxer) throws EmpresaExcepcio {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            File f = new File(nomFitxer + ".xml");
            doc = builder.parse(f);
        } catch (Exception ex) {
            throw new EmpresaExcepcio("GestorXML.carrega");
        }
    }

    private void fitxerEmpresa() throws EmpresaExcepcio {
        Element arrel = doc.getDocumentElement();
        Integer code = Integer.parseInt(arrel.getAttribute("Codi"));
        String nom = arrel.getAttribute("Nom");
        String address = arrel.getAttribute("Adreça");
        empresa = new Empresa(code, nom, address);

        for (int i = 0; i < arrel.getChildNodes().getLength(); i++) {
            NodeList fills = arrel.getChildNodes();
            Node n = fills.item(i);
            Element elem = (Element) n;
            if ("Ordinador".equals(n.getNodeName())) {
                String codi = elem.getAttribute("Codi");
                String descripcio = elem.getAttribute("Descripcio");
                String marca = elem.getAttribute("Marca");
                int memoria = Integer.parseInt(elem.getAttribute("Memoria"));
                int velocitat = Integer.parseInt(elem.getAttribute("Velocitat"));
                Ordinador ordinador = new Ordinador(codi, descripcio, marca, memoria, velocitat);
                empresa.addOrdinador(ordinador);
            }
            if ("Impressora".equals(n.getNodeName())) {
                String codi = elem.getAttribute("Codi");
                String descripcio = elem.getAttribute("Descripcio");
                String marca = elem.getAttribute("Marca");
                int memoria = Integer.parseInt(elem.getAttribute("Memoria"));
                int tipus = Integer.parseInt(elem.getAttribute("Tipus"));
                Impressora impressora = new Impressora(codi, descripcio, marca, memoria, tipus);
                empresa.addImpressora(impressora);
            }
            if ("Moble".equals(n.getNodeName())) {
                String codi = elem.getAttribute("Codi");
                String name = elem.getAttribute("Nom");
                int alc = Integer.parseInt(elem.getAttribute("Alcada"));
                int ample = Integer.parseInt(elem.getAttribute("Ample"));
                int fons = Integer.parseInt(elem.getAttribute("Fons"));
                Moble moble = new Moble(codi, name, alc, ample, fons);
                empresa.addMoble(moble);
            }
            if ("Nau".equals(n.getNodeName())) {
                String codi = elem.getAttribute("Codi");
                String name = elem.getAttribute("Nom");
                Nau nau = new Nau(codi, name);
                empresa.addNau(nau);
                NodeList subFills = n.getChildNodes();
                for (int j = 0; j < subFills.getLength(); j++) {
                    Node subN = subFills.item(j);
                    Element subElem = (Element) subN;
                    if ("Ordinador".equals(subN.getNodeName())) {
                        String sCodi = subElem.getAttribute("Codi");
                        nau.addActiuNau(empresa.getActius()[empresa.selectActiu(1, sCodi)]);
                    }
                    if ("Impressora".equals(subN.getNodeName())) {
                        String sCodi = subElem.getAttribute("Codi");
                        nau.addActiuNau(empresa.getActius()[empresa.selectActiu(2, sCodi)]);
                    }
                    if ("Moble".equals(subN.getNodeName())) {
                        String sCodi = subElem.getAttribute("Codi");
                        nau.addActiuNau(empresa.getActius()[empresa.selectActiu(3, sCodi)]);
                    }
                }

            }
        }

    }

}
